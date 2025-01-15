package com.example.java_code_learn_spring_pageable.service.impl;

import com.example.java_code_learn_spring_pageable.dto.BookDto;
import com.example.java_code_learn_spring_pageable.mapper.AuthorMapper;
import com.example.java_code_learn_spring_pageable.mapper.BookMapper;
import com.example.java_code_learn_spring_pageable.model.Book;
import com.example.java_code_learn_spring_pageable.repository.BookRepository;
import com.example.java_code_learn_spring_pageable.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book createBook(BookDto dto) {
        logger.info("was invoked book create method in service");
        checkExistEntity(dto);
        return repository.save(bookMapper.mapDtoToEntity(dto));
    }

    @Override
    public Book updateBook(Long id, BookDto dto) {
        logger.info("was invoked book update method in service");
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        book.setBookName(dto.getBookName());
        book.setAuthor(authorMapper.mapDtoToEntity(dto));
        repository.save(book);
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        logger.info("was invoked book delete method in service");
        repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.deleteById(id);
    }

    @Override
    public Book getBookInfo(Long id) {
        logger.info("was invoked get book info method in service");
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return book;
    }

    @Override
    public Page<Book> getBooksPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private void checkExistEntity(BookDto dto) {
        Book book = repository.findByBookName(dto.getBookName());
        if (book != null) {
            throw new RuntimeException("Already exist");
        }
    }
}
