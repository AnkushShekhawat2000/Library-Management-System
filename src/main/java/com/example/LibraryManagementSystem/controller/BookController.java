package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book)
    {
        try{
            String response = bookService.addBook(book);
            return response;
        }
        catch(AuthorNotFoundException e)
        {
            return e.getMessage();
        }


    }


}
