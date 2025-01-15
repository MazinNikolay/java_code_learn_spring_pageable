package com.example.java_code_learn_spring_pageable.service;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book createBook(BookDto dto);

    Book updateBook(Long id, BookDto dto);

    void deleteBook(Long id);

    Book getBookInfo(Long id);

    Page<Book> getBooksPageable(Pageable pageable);
}
