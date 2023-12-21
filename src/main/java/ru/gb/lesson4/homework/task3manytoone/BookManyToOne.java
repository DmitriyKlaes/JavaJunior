package ru.gb.lesson4.homework.task3manytoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookmanytoone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookManyToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AuthorManyToOne author;

}
