package ru.gb.lesson4.homework;

import ru.gb.lesson4.homework.task3manytoone.AuthorManyToOne;
import ru.gb.lesson4.homework.task3manytoone.BookManyToOne;
import ru.gb.lesson4.homework.task3onetomany.AuthorOneToMany;
import ru.gb.lesson4.homework.task3onetomany.BookOneToMany;

import java.util.*;

public class EntityCreator {
    public static Map<String, String[]> authorsAndBooks =
            new HashMap<>(Map.of("Лев Николаевич Толстой", new String[]{"Анна Каренина", "Детство", "После бала"},
                                 "Иван Сергеевич Тургенев", new String[]{"Муму", "Отцы и дети", "Накануне"},
                                 "Антон Павлович Чехов", new String[]{"Дама с собачкой", "Человек в футляре", "Студент"}));

    public static List<BookManyToOne> createListBooks() {
        List<BookManyToOne> result = new ArrayList<>();

        for (Map.Entry<String, String[]> mapEntry : authorsAndBooks.entrySet()) {
            AuthorManyToOne author = new AuthorManyToOne();
            author.setName(mapEntry.getKey());

            for (String bookName : mapEntry.getValue()) {
                BookManyToOne book = new BookManyToOne();
                book.setName(bookName);
                book.setAuthor(author);
                result.add(book);
            }
        }
        return result;
    }

    public static List<AuthorOneToMany> createListAuthors() {
        List<AuthorOneToMany> result = new ArrayList<>();

        for (Map.Entry<String, String[]> mapEntry : authorsAndBooks.entrySet()) {
            AuthorOneToMany author = new AuthorOneToMany();
            author.setName(mapEntry.getKey());
            author.setBooks(new ArrayList<>());

            for (String bookName : mapEntry.getValue()) {
                BookOneToMany book = new BookOneToMany();
                book.setName(bookName);
                author.getBooks().add(book);
                result.add(author);
            }
        }
        return result;
    }
}
