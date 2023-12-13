package ru.gb.lesson3.homework;

import java.io.Serializable;

public class SerObject implements Serializable {

    String text;
    int number;

    public SerObject(String text, int number) {
        this.text = text;
        this.number = number;
    }

    @Override
    public String toString() {
        return "SerObject{" +
                "text='" + text + '\'' +
                ", number=" + number +
                '}';
    }
}
