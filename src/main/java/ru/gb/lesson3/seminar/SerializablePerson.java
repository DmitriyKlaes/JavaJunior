package ru.gb.lesson3.seminar;

import java.io.Serial;

public class SerializablePerson {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private int age;
    private transient Department department;

    public SerializablePerson(String name, int age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public SerializablePerson(String name, Department department) {
        this(name, 20, department);
    }

    public SerializablePerson(String name) {
        this(name, 20, null);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SerializablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                '}';
    }
}
