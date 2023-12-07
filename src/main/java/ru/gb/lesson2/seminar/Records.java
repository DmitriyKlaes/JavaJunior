package ru.gb.lesson2.seminar;

public class Records {

  record MyRecord(int x, int y) {

  }

  public static void main(String[] args) {
    MyRecord myRecord = new MyRecord(2, 5);
    int x = myRecord.x();
    int y = myRecord.y();


    System.out.println(myRecord);
  }

}
