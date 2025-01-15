package com.example.java_code_learn_spring_pageable.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "name")
    @NotBlank
    private String bookName;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "author_id")
    private Author author;
}
