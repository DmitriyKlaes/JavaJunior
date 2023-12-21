package ru.gb.lesson4.homework.task2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;
}
