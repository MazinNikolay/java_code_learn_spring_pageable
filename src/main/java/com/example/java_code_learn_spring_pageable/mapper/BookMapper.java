package com.example.java_code_learn_spring_pageable.mapper;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.model.Author;
import com.example.java_code_learn_spring_pageable.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorMapper mapper;

    public Book mapDtoToEntity(BookDto dto) {
        Author author = mapper.mapDtoToEntity(dto);
        return Book.builder()
                .bookName(dto.getBookName())
                .author(author)
                .build();
    }
}
