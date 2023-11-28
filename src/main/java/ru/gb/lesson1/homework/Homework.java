package ru.gb.lesson1.homework;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Homework {
    public static void main(String[] args) {
//         * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Long> listNumbers = Stream.generate(() -> ThreadLocalRandom.current().nextLong(1_000_000))
                .limit(1_000)
                .toList();


//         * 1.1 Найти максимальное
        long maxValue = listNumbers.stream().max(Comparator.naturalOrder()).get();
        System.out.printf("Максимальное значание: %d%n", maxValue);


//         * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
        Optional<Long> resultOperation = listNumbers.stream()
                .filter(it -> it > 500_000)
                .map(it -> it * 5 - 150)
                .reduce(Long::sum);
        System.out.println(resultOperation.map(aLong -> "Сумма отсортированных чисел: " + aLong)
                .orElse("Совпадений нет!"));


//         * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
        long resultCountNumbers = listNumbers.stream()
                .filter(it -> it * it < 100_000)
                .count();
        System.out.printf("Колличество чисел чей квадрат меньше 100_000: %d%n", resultCountNumbers);


//         * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
//         * 2.1 Создать список из 10-20 сотрудников
        List<Employee> listEmployee = new ArrayList<>(Arrays.asList(
                new Employee("Вася", 18, 7000, "Продажи"),
                new Employee("Толя", 19, 8000, "Продажи"),
                new Employee("Гера", 22, 9000, "Продажи"),
                new Employee("Зевс", 36, 10000, "Продажи"),
                new Employee("Виталик", 34, 6500, "Продажи"),

                new Employee("Валера", 60, 7500, "Логистика"),
                new Employee("Зухра", 30, 7500, "Логистика"),
                new Employee("Загорелий", 30, 7500, "Логистика"),
                new Employee("Загорелия", 31, 15000, "Логистика"),
                new Employee("Галя", 25, 23000, "Логистика"),

                new Employee("Таня", 28, 15000, "IT-отдел"),
                new Employee("Дима", 22, 170000, "IT-отдел"),
                new Employee("Вова", 37, 50000, "IT-отдел"),
                new Employee("Катя", 38, 50000, "IT-отдел"),
                new Employee("Рома", 39, 75000, "IT-отдел"),

                new Employee("Юля", 44, 23000, "Склад"),
                new Employee("Аня", 41, 2000, "Склад"),
                new Employee("Леша", 40, 2000, "Склад"),
                new Employee("Саша", 33, 30000, "Склад"),
                new Employee("Маша", 50, 36000, "Склад")
        ));


//         * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
        listEmployee.stream()
                .map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);


//         * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        listEmployee.stream()
                .filter(it -> it.getSalary() < 10_000)
                .forEach(it -> it.setSalary(it.getSalary() * 1.2));


//         * 2.4 * Из списка сотрудников с помощью стрима создать
//         Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
        listEmployee.stream().collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((k, v) -> System.out.printf("Отдел \"%s\": %s%n", k, v.stream()
                        .map(Employee::getName)
                        .toList()));


//         * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double>
//         с отделами и средней зарплатой внутри отдела
        listEmployee.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)))
                .forEach((k, v) -> System.out.printf("В отделе \"%s\" средняя зп = %s%n", k, v));

    }
}
