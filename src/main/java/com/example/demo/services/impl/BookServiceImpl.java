package com.example.demo.services.impl;

import com.example.demo.dto.BookDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtoList = new ArrayList<>();

        books.forEach(book -> {
            BookDto dto = bookMapper.toDto(book);
            bookDtoList.add(dto);
        });

        return bookDtoList;
    }

    @Override
    public BookDto addBook(BookDto dto) {
        Book book = bookMapper.toEntity(dto);

        if (dto.getAuthor() != null && dto.getAuthor().getId() != null) {
            Author author = authorRepository.findById(dto.getAuthor().getId()).orElse(null);
            book.setAuthor(author);
        }

        Book createdBook = bookRepository.save(book);
        BookDto createdBookDto = bookMapper.toDto(createdBook);

        return createdBookDto;
    }

    @Override
    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (Objects.isNull(book)) {
            return null;
        }

        BookDto dto = bookMapper.toDto(book);
        return dto;
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        BookDto checkBook = getBook(id);

        if (Objects.isNull(checkBook)) {
            return null;
        }

        Book book = bookMapper.toEntity(dto);
        book.setId(id);

        if (dto.getAuthor() != null && dto.getAuthor().getId() != null) {
            Author author = authorRepository.findById(dto.getAuthor().getId()).orElse(null);
            book.setAuthor(author);
        }

        Book updatedBook = bookRepository.save(book);
        BookDto updatedBookDto = bookMapper.toDto(updatedBook);

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
}