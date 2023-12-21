package ru.gb.lesson4.seminar;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // user_audit
// публичный конструктор без аргументов
// геттеры и сеттеры для каждого поля
// у каждой сущности (entity) есть первичный ключ
public class User {

  public User() {

  }

  public User(Long id) {
    this.id = id;
  }

  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
