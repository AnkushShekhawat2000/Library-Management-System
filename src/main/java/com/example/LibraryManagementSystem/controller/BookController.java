package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // delete a book

    // give me names of bill books of a particulat genre and coat

    @GetMapping("/get-by-genre-cost")
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre, @RequestParam("cost") double cost)
    {
        return bookService.getBooksByGenreAndCostGreaterThan(genre,cost);
    }

    // all the books having no of pages

    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre, @RequestParam("cost") double cost)
    {
        return bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);
    }

}
