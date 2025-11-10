package com.example.demo.services;


import com.example.demo.dto.BookDto;
import java.util.List;

public interface BookService {
    List<BookDto> getBooks();
    BookDto addBook(BookDto dto);
    BookDto getBook(Long id);
    BookDto updateBook(Long id, BookDto dto);
    boolean deleteBook(Long id);
}