package ru.gb.lesson2.seminar;

import java.util.List;
import java.util.Optional;

public class Questions {

  public static void main(String[] args) {
    List<Integer> ints = List.of();

    Optional<Integer> max = ints.stream()
      .max(Integer::compare);
    max.ifPresent(Questions::save);





  }

  private static void save(int value) {
    //
  }

}
