package ru.gb.lesson3.homework;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Homework {

    /**
     * Написать класс с двумя методами:
     * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл.
     * Название файл - class.getName() + "_" + UUID.randomUUID().toString()
     * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString()
     * и загружает объект из файла и удаляет этот файл.
     *
     * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
     */

}

class SerWork {

    public static void main(String[] args) {
        SerObject serObject = new SerObject("test", 1);
        String directoryForSer = "src/main/java/ru/gb/lesson3/homework/serfiles";

        String serFileName = serToFile(serObject, directoryForSer);

        Object deSerObject = deSerFromFile(serFileName, directoryForSer);
        System.out.println(deSerObject);
    }

    public static String serToFile(Serializable objectForSer, String directory) {
        if (objectForSer == null) {
            throw new RuntimeException("Объект не может быть null!");
        }
        String fileName = objectForSer.getClass().getName()+ "_" + UUID.randomUUID()+".txt";
        Path path = Path.of(directory, fileName);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(objectForSer);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Object deSerFromFile(String fileName, String directory) {
        Path path = Path.of(directory, fileName);
        Object resultObject;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            resultObject = objectInputStream.readObject();
            Files.delete(path);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return resultObject;
    }
}
