package ru.gb.lesson2.seminar;

import lombok.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Lesson2Main {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
    // annotation processor

    // Reflection

    Class<Person> personClass = Person.class;
    Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);

    constructor.setAccessible(true);
    Person person = constructor.newInstance("Igor"); // new Person("Igor")
    System.out.println(person);

    Field ageField = personClass.getDeclaredField("age");
    ageField.setAccessible(true);
    ageField.setInt(person, 160);
    System.out.println(person);

    Field nameField = personClass.getDeclaredField("name");
    nameField.setAccessible(true);
    nameField.set(person, "Igor - 2");
    System.out.println(person);

    Method toStringMethod = personClass.getMethod("toString");
    Object toStringResult = toStringMethod.invoke(person);
    System.out.println(toStringResult);

    Method randomPersonMethod = personClass.getMethod("randomPerson");
    Object randomPerson = randomPersonMethod.invoke(null);
    System.out.println(randomPerson);

    System.out.println(Person.class.isAssignableFrom(Head.class));
    System.out.println(Head.class.isAssignableFrom(Person.class));

  }

  static class Head extends Person {
    public Head(String name) {
      super(name);
    }
  }

  @Data
  public static class Person {
    private static long counter = 1L;

    private final String name;
    private int age;

    private Person(String name) {
      this(name, 20);
    }

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public static Person randomPerson() {
      return new Person("Person #" + counter++);
    }

    @Override
    public String toString() {
      return String.format("%s - [%s]", name, age);
    }
  }
}
