package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.model.Book;

public class BookTransformer {

    public static BookResponse BookToBookResponse(Book book)
    {
        return BookResponse.builder()
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPAges(book.getNoOfPages())
                .title(book.getTitle())
                .build();
    }
}
