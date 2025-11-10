package com.example.demo.services.impl;


import com.example.demo.dto.BookDto;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtoList = new ArrayList<>();

        books.forEach(book -> {
            BookDto dto = toDto(book);
            bookDtoList.add(dto);
        });

        return bookDtoList;
    }

    @Override
    public BookDto addBook(BookDto dto) {
        Book book = toEntity(dto);

        Book createdBook = bookRepository.save(book);

        BookDto createdBookDto = toDto(createdBook);

        return createdBookDto;
    }

    @Override
    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (Objects.isNull(book)) {
            return null;
        }

        BookDto dto = toDto(book);
        return dto;
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        BookDto checkBook = getBook(id);

        if (Objects.isNull(checkBook)) {
            return null;
        }

        Book book = toEntity(dto);

        Book updatedBook = bookRepository.save(book);

        BookDto updatedBookDto = toDto(updatedBook);
        return updatedBookDto;
    }

    @Override
    public boolean deleteBook(Long id) {
        BookDto checkBook = getBook(id);

        if (Objects.isNull(checkBook)) {
            return false;
        }

        bookRepository.deleteById(id);

        return true;
    }

    private BookDto toDto(Book book) {
        BookDto bookDto = BookDto
                .builder()
                .id(book.getId())
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .price(book.getPrice())
                .genre(book.getGenre())
                .build();

        return bookDto;
    }

    private Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getBookTitle());
        book.setAuthor(dto.getBookAuthor());
        book.setPrice(dto.getPrice());
        book.setGenre(dto.getGenre());
        return book;
    }
}
