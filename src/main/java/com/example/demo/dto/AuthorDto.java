package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Long id;
    private String name;
    private String bio;
}