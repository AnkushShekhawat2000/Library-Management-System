package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponseDto;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(Book book) {

        // author does exists or not
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author id!!!");
        }

        Author author = authorOptional.get();
        book.setAuthor(author);

        author.getBooks().add(book);

        authorRepository.save(author); // this will save author and book both

        return "Book added susscessfully";
    }

    public List<BookResponseDto> getBooksByGenreAndCostGreaterThan(String genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);

        // prepare the response
        // convert model to dto

        List<BookResponseDto> response =new ArrayList<>();

        for(Book book : books)
        {
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setCost(book.getCost());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setNoOfPAges(book.getNoOfPages());
            bookResponseDto.setAuthorName(book.getAuthor().getName());
            response.add(bookResponseDto);
        }

        return response;
    }

    public List<BookResponseDto> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {

      List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

      // prepare the response
        // convert model to dto

        List<BookResponseDto> response =new ArrayList<>();

        for(Book book : books)
        {
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setCost(book.getCost());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setNoOfPAges(book.getNoOfPages());
            bookResponseDto.setAuthorName(book.getAuthor().getName());
            response.add(bookResponseDto);
        }

        return response;
    }
}
