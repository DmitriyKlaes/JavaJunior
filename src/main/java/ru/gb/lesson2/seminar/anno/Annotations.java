package ru.gb.lesson2.seminar.anno;

public class Annotations {

  public static void main(String[] args) throws NoSuchFieldException {
    MyClass myClass = new MyClass();
    System.out.println(myClass.getNegative()); // 0
    System.out.println(myClass.getPositive()); // 0

    RandomIntegerProcessor.processObject(myClass);

    System.out.println(myClass.getNegative()); // <0
    System.out.println(myClass.getPositive()); // >0
  }

  static class MyClass {

    @RandomInteger(minValue = -15, maxValue = -1)
    private int negative;

    @RandomInteger(minValue = 1, maxValue = 20)
    private int positive;

    public int getNegative() {
      return negative;
    }

    public int getPositive() {
      return positive;
    }
  }

}
