package com.example.java_code_learn_spring_pageable.repository;

import com.example.java_code_learn_spring_pageable.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);
}
