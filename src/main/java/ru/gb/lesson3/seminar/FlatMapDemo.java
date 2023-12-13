package ru.gb.lesson3.seminar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class FlatMapDemo {

    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();

        // List<Employee>

        // employee -> employee.stream()

        List<Employee> list = departments.stream()
                .map(Department::getEmployees)
                .flatMap(Collection::stream)
                .toList();
        UUID.randomUUID().toString();

    }

    static class Department {
        private List<Employee> employees;

        public List<Employee> getEmployees() {
            return employees;
        }
    }

    static class Employee {

    }


}
