package ru.gb.lesson2.homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestProcessor {

  /**
   * Данный метод находит все void методы без аргументов в классе, и запускеет их.
   * <p>
   * Для запуска создается тестовый объект с помощью конструткора без аргументов.
   */
  public static void runTest(Class<?> testClass) {
    final Constructor<?> declaredConstructor;
    try {
      declaredConstructor = testClass.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
    }

    final Object testObj;
    try {
      testObj = declaredConstructor.newInstance();
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
    }

    /*
     * 1. Добавить аннотации BeforeEach, AfterEach,
     * которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
     */
    List<Method> beforeEachMethods = new ArrayList<>();
    List<Method> standardMethods = new ArrayList<>();
    List<Method> afterEachMethods = new ArrayList<>();
    for (Method method : testClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(BeforeEach.class)) {
        checkTestMethod(method);
        beforeEachMethods.add(method);
      } else if (method.isAnnotationPresent(AfterEach.class)) {
        checkTestMethod(method);
        afterEachMethods.add(method);
        /*
         * 3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
         */
      } else if (method.isAnnotationPresent(Test.class) && !method.isAnnotationPresent(Skip.class)) {
        checkTestMethod(method);
        standardMethods.add(method);
      }
    }

    /*
     * 2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
     * Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
     * Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
     * то порядок вызовов first -> second -> third
     */
    standardMethods.sort(Comparator.comparingInt(it -> it.getAnnotation(Test.class).order()));

    for (Method standardMethod : standardMethods) {
      beforeEachMethods.forEach(it -> runTest(it, testObj));
      runTest(standardMethod, testObj);
      afterEachMethods.forEach(it -> runTest(it, testObj));
    }

  }

  private static void checkTestMethod(Method method) {
    if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
      throw new IllegalArgumentException("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
    }
  }

  private static void runTest(Method testMethod, Object testObj) {
    try {
      testMethod.invoke(testObj);
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
    } catch (AssertionError e) {

    }
  }

}
