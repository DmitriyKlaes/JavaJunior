package ru.gb.lesson4.homework.task3onetomany;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookonetomany")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

}
