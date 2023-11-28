package ru.gb.lesson1.seminar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lesson1Main {

  public static void main(String[] args) {
    List<String> strings = new ArrayList<>(List.of("java", "c#", "c++", "python", "kotlin", "perl", "pascal"));
    // method reference
    strings.sort(Lesson1Main::myComparator);

    System.out.println(strings);

//    System.out.println(strings);
//
//    strings.sort((o1, o2) -> o1.length() - o2.length());
//    System.out.println(strings);

//    MyInterface myInterface = it -> System.err.print(it);
//    myInterface.foo("abcde");
//    myInterface.foo("abcde");
//    myInterface.foo("abcde");

    // void run() -> Runnable
    // T get();   -> Supplier
    // void accept(T value); -> Consumer
    // R apply(T value); -> Function
    //


    Runnable run = Lesson1Main::printRandomNumberLessThan100;
    for (int i = 0; i < 10; i++) {
      run.run();
    }

    // string -> integer
    Function<String, Integer> stringLengthExtractor = String::length;
    System.out.println(stringLengthExtractor.apply("abcde"));
    System.out.println(stringLengthExtractor.apply("java"));
    System.out.println(stringLengthExtractor.apply("kotlin"));

    String java = "java";
    Supplier<Integer> javaLengthGetter = java::length;

    System.out.println(javaLengthGetter.get());
    System.out.println(javaLengthGetter.get());
    System.out.println(javaLengthGetter.get());
    System.out.println(javaLengthGetter.get());

    // string -> boolean
    Predicate<String> isBestLanguage = "[AZ-az]"::matches;

    System.out.println(isBestLanguage.test("java"));
    System.out.println(isBestLanguage.test("c++"));
    System.out.println(isBestLanguage.test("python"));

    UnaryOperator<String> f = it -> it.toUpperCase();
    System.out.println(f.apply("Igor"));





//    Supplier<Person> personGenerator = Person::new;
    Function<String, Person> personFunction = Person::new;

    Person person = personFunction.apply("Igor"); // new Person("Igor")
    System.out.println(person);

    person = personFunction.apply("OWNER");
    System.out.println(person);
  }

  public static class Person {
    private static long counter = 1L;
    private String name;

    private Supplier<String> wordGenerator;

    public Person(String name) {
      this.name = name;
      this.wordGenerator = this::generateNextWord;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void saySomething() {
      System.out.println(wordGenerator.get());
    }

    private String generateNextWord() {
      return "EFFECTIVE RANDOM WORD";
    }

    @Override
    public String toString() {
      return name;
    }
  }

  // Comparator<String>
  static int myComparator(String a, String b) {
    return a.length() - b.length();
  }

  // Runnable
  static void printRandomNumberLessThan100() {
    System.out.print(ThreadLocalRandom.current().nextInt(100));
  }

  // Function<String, Integer>
  static Integer extractStringLength(String arg) {
    return arg.length();
  }


  interface MyInterface {

    void foo(String arg);

  }

}
