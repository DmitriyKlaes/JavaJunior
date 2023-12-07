package ru.gb.lesson2.seminar.anno;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerProcessor {

  // Найти все int-поля класса с аннотацией RandomInteger, и заполнить их рандомными значениями
  public static void processObject(Object object) {
    Class<?> objClass = object.getClass();
    for (Field declaredField : objClass.getDeclaredFields()) {
      if (int.class.isAssignableFrom(declaredField.getType()) && declaredField.isAnnotationPresent(RandomInteger.class)) {
        RandomInteger annotation = declaredField.getAnnotation(RandomInteger.class);
        int minValue = annotation.minValue();
        int maxValue = annotation.maxValue();

        // [4, 7] => 4 + (0, 3)
        int randomValue = minValue + ThreadLocalRandom.current().nextInt(maxValue - minValue);
        declaredField.setAccessible(true);
        try {
          declaredField.set(object, randomValue);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e.getMessage(), e);
        }
      }
    }
  }

}
