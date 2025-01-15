package com.example.java_code_learn_spring_pageable.controller;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.model.Book;
import com.example.java_code_learn_spring_pageable.service.impl.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookServiceImpl service;

    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;
    private static Book book5;

    @BeforeAll
    static void setUp() {
        book1 = Book.builder()
                .bookId(1L)
                .bookName("book1")
                .build();
        book2 = Book.builder()
                .bookId(2L)
                .bookName("book2")
                .build();
        book3 = Book.builder()
                .bookId(3L)
                .bookName("book3")
                .build();
        book3 = Book.builder()
                .bookId(3L)
                .bookName("book3")
                .build();
        book4 = Book.builder()
                .bookId(4L)
                .bookName("book4")
                .build();
        book5 = Book.builder()
                .bookId(5L)
                .bookName("book5")
                .build();
    }

    @Test
    void createBook() throws Exception {
        when(service.createBook(any(BookDto.class))).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.post("/app/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName")
                        .value("book1"));
    }

    @Test
    void updateBook() throws Exception {
        when(service.updateBook(anyLong(), any(BookDto.class))).thenReturn(book2);
        mockMvc.perform(MockMvcRequestBuilders.put("/app/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book2)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName")
                        .value("book2"));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/app/book/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBooksFirstPage() throws Exception {
        PageRequest request = PageRequest.of(0, 3);
        List<Book> booksFirstPage = new ArrayList<>(List.of(book1,
                book2, book3));

        Page<Book> bookPage = new PageImpl<>(booksFirstPage, request, 3);
        when(service.getBooksPageable(any(Pageable.class))).thenReturn(bookPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/book")
                        .param("page", "0")
                        .param("size", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.content[0].bookName").value("book1"));
    }

    @Test
    void getBooksSecondPage() throws Exception {
        PageRequest request = PageRequest.of(1, 3);
        List<Book> booksSecondPage = new ArrayList<>(List.of(book4, book5));

        Page<Book> bookPage = new PageImpl<>(booksSecondPage, request, 2);
        when(service.getBooksPageable(any(Pageable.class))).thenReturn(bookPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/book")
                        .param("page", "1")
                        .param("size", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.content[0].bookName").value("book4"));
    }

    @Test
    void getBookData() throws Exception {
        when(service.getBookInfo(anyLong())).thenReturn(book3);
        mockMvc.perform(MockMvcRequestBuilders.get("/app/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book3)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName")
                        .value("book3"));
    }
}