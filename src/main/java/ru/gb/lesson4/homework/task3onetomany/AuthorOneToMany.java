package ru.gb.lesson4.homework.task3onetomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authoronetomany")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "author_id")
    private List<BookOneToMany> books;

}