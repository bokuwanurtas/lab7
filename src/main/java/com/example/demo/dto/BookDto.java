package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDto {
    private Long id;
    private String bookTitle;
    private String bookAuthor;
    private int price;
    private String genre;
}