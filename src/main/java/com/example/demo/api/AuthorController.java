package com.example.demo.api;

import com.example.demo.dto.AuthorDto;
import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        List<AuthorDto> authorDtoList = new ArrayList<>();

        authors.forEach(author -> {
            AuthorDto dto = AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .bio(author.getBio())
                    .build();
            authorDtoList.add(dto);
        });

        return authorDtoList;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBio(dto.getBio() != null ? dto.getBio() : "");

        Author saved = authorRepository.save(author);

        AuthorDto response = AuthorDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .bio(saved.getBio())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorDto dto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .build();

        return ResponseEntity.ok(dto);
    }
}