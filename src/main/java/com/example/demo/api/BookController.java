package com.example.demo.api;


import com.example.demo.dto.BookDto;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
        List<BookDto> bookDtoList = bookService.getBooks();

        if (bookDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(bookDtoList);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBook(@PathVariable(value = "id") Long id) {
        BookDto bookDto = bookService.getBook(id);

        if (Objects.isNull(bookDto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(bookDto);
        }
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        BookDto createdBookDto = bookService.addBook(bookDto);
        return new ResponseEntity<>(createdBookDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "id") Long id, @RequestBody BookDto bookDto) {
        BookDto updatedBookDto = bookService.updateBook(id, bookDto);

        if (Objects.isNull(updatedBookDto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(updatedBookDto);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {
        boolean deleteBook = bookService.deleteBook(id);

        if (deleteBook) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}