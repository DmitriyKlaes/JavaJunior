package ru.gb.lesson3.seminar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lesson3Main {
    // Thread
    // Stream

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Java Database Connectivity
        Department department = new Department("gb");
        SerializablePerson igor = new SerializablePerson("Igor", 180, department);

        Path path = Path.of("serializable-person.txt");

//        OutputStream outputStream = Files.newOutputStream(path);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(igor);
//        objectOutputStream.close();
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
//        SerializablePerson deserializedIgor = (SerializablePerson) objectInputStream.readObject();
//        System.out.println(deserializedIgor);
//        objectInputStream.close();
//
        Path myfile = Path.of( "myfile.txt");

//        Path parent = myfile.getParent();
//        Files.createDirectory(parent);
//
//        Files.createFile(myfile);

        Files.writeString(myfile, "asdf");


    }


}
