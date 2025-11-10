package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "books")
@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private int price;

    @Column(name = "genre")
    private String genre;
}