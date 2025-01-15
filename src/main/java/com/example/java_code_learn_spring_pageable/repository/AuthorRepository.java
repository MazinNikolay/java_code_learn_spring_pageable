package com.example.java_code_learn_spring_pageable.repository;

import com.example.java_code_learn_spring_pageable.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthorName(String authorName);
}
