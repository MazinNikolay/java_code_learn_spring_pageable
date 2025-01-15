package com.example.java_code_learn_spring_pageable.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    @Schema(description = "Название книги", minLength = 2)
    private String bookName;

    @Schema(description = "Имя автора", minLength = 2)
    private String authorName;
}
