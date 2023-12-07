package ru.gb.lesson1.lesson1.seminar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class StreamDemo {

  public static void main(String[] args) {
    List<Integer> list = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
      .limit(100)
      .toList();



    System.out.println(list);


    List<Product> products = generateRandomProducts();

    // Найти и распечатать все товары из категории "Электроника" дешевле 10_000
    products.stream()
      .filter(it -> it.getCategory().equals("Электроника"))
      .filter(it -> it.getCost() < 10_000)
      .forEach(System.out::println);

    // Всем продуктам из категории "Продукты" повысить стоимость на 5%
    products.stream()
      .filter(it -> it.getCategory().equals("Продукты"))
      .forEach(it -> it.setCost(it.getCost() * 1.05));

    List<Product> existCollection = new ArrayList<>();

    // Все продукты, начинающиеся на М, собрать в список
//    TreeSet<Product> м = products.stream()
//      .filter(it -> it.getName().startsWith("М"))
//      .collect(Collectors.toCollection(TreeSet::new));

//    if (product.isPresent()) {
//      Product productValue = product.get();
//      System.out.println(productValue);
//    } else {
//      System.out.println("Объектаа нет!");
//    }

//    product.ifPresentOrElse(x -> System.out.println(x), () -> System.out.println("Объектаа нет!"));



  }

  private static List<Product> generateRandomProducts() {
    return List.of(
      new Product("Молоко", 70, "Продукты"),
      new Product("Хлеб", 53, "Продукты"),
      new Product("Вода", 50, "Продукты"),
      new Product("Чипсы", 110, "Продукты"),
      new Product("Макароны", 11_000, "Продукты"),
      new Product("Булугур", 73, "Продукты"),

      new Product("Компьютер", 70_000, "Электроника"),
      new Product("Наушники", 7_000, "Электроника"),
      new Product("Клавиатура", 3_500, "Электроника"),

      new Product("Глицин", 100, "Лекарства"),
      new Product("Парацетомол", 120, "Лекарства"),
      new Product("Бинт", 15, "Лекарства"),
      new Product("Маска", 33, "Лекарства")
    );
  }

  static class Product {
    private final String name;
    private double cost;
    private String category;

    public Product(String name, double cost, String category) {
      this.name = name;
      this.cost = cost;
      this.category = category;
    }

    public String getName() {
      return name;
    }

    public double getCost() {
      return cost;
    }

    public String getCategory() {
      return category;
    }

    public void setCost(double cost) {
      this.cost = cost;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    @Override
    public String toString() {
      return String.format("[%s] (cost = %s, category = %s)", name, cost, category);
    }
  }

}
