package com.example.java_code_learn_spring_pageable.mapper;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.model.Author;
import com.example.java_code_learn_spring_pageable.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorMapper {
    private final AuthorRepository authorRepository;

    public Author mapDtoToEntity(BookDto dto) {
        Author author = authorRepository.findByAuthorName(dto.getAuthorName()).
                orElseGet(() -> Author.builder()
                        .authorName(dto.getAuthorName())
                        .build());
        authorRepository.save(author);
        return author;
    }
}
