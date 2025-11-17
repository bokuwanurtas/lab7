package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookSummaryDto;
import com.example.demo.models.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {

    @Mapping(target = "bookTitle", source = "title")
    @Mapping(target = "author", source = "author")
    BookDto toDto(Book book);

    @Mapping(target = "title", source = "bookTitle")
    @Mapping(target = "author", ignore = true) // вручную в сервисе
    Book toEntity(BookDto dto);

    BookSummaryDto toSummaryDto(Book book);
}