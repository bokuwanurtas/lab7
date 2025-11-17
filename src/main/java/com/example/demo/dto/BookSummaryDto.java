package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSummaryDto {
    private Long id;
    private String bookTitle;
    private int price;
}