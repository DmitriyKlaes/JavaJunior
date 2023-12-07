package ru.gb.lesson1.lesson1.seminar;

import java.util.concurrent.atomic.AtomicInteger;

public class ClosureMain {

  public static void main(String[] args) {
    Runnable runnable = xPrinter();
    runnable.run();

//    Consumer<String> lambda = objects -> System.out.println(objects);
//    Consumer<String> referenceMethod = System.out::println;
//    System.setOut(null);
//    referenceMethod.accept("123");
//    lambda.accept("123");

  }

  private static Runnable xPrinter() {
    // effectively final

    AtomicInteger atomicInteger = new AtomicInteger(5);
    Runnable runnable = () -> {
      System.out.println(atomicInteger.get());
    };

    atomicInteger.set(7);


    return runnable;
  }

}
