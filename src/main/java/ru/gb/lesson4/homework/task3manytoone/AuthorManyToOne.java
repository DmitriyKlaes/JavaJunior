package ru.gb.lesson4.homework.task3manytoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authormanytoone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorManyToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

}