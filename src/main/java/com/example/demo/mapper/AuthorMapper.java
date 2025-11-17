package com.example.demo.mapper;

import com.example.demo.dto.AuthorSummaryDto;
import com.example.demo.models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {

    @Mapping(target = "name", source = "name")
    AuthorSummaryDto toSummaryDto(Author author);
}