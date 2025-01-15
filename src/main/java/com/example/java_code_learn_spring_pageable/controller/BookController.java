package com.example.java_code_learn_spring_pageable.controller;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.model.Book;
import com.example.java_code_learn_spring_pageable.service.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Контроллер для работы с книгами")
@RequestMapping("app/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl service;

    @Operation(summary = "Добавление книги")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    public ResponseEntity<Book> createBook( @RequestBody BookDto dto) {
        Book book = service.createBook(dto);
        return ResponseEntity.ok().body(book);
    }

    @Operation(summary = "Обновление данных книги")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @Valid @RequestBody BookDto dto) {
        Book book = service.updateBook(id, dto);
        return ResponseEntity.ok().body(book);
    }

    @Operation(summary = "Удаление книги")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение перечня книг")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
        return ResponseEntity.ok().body(service.getBooksPageable(pageable));
    }

    @Operation(summary = "Получение детальной информации о книге")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Book not found")
    })
    public ResponseEntity<Book> getBookData(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getBookInfo(id));
    }
}
