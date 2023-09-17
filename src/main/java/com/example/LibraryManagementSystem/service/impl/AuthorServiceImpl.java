package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {

        Author savedAuthor = authorRepository.save(author);

        return "Author successfully added!!!";

    }
}
