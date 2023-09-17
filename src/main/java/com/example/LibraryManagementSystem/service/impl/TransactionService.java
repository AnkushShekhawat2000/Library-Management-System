package com.example.LibraryManagementSystem.service.impl;


import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.dto.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.exception.BookNotAvailableException;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public IssueBookResponse issueBook(int bookId, int studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
         if(studentOptional.isEmpty())
         {
             throw new StudentNotFoundException("Invalid student id!!");
         }

         Optional<Book> optionalBook = bookRepository.findById(bookId);
         if(optionalBook.isEmpty())
         {
            throw new BookNotAvailableException("Invalid biik id");
         }

         Book book = optionalBook.get();
         if(book.isIssued())
         {
             throw new BookNotAvailableException("Book already issued");
         }

         // issue the book

        Student student = studentOptional.get();

         // create transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        // update book

        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTranscactons().add(savedTransaction);

       Book savedBook = bookRepository.save(book);   // book  and transaction
       Student savedStudent = studentRepository.save(student);  // student and transaction

        //send an mail

        String text = student.getName()+"The below book has been issued to you\n"+
                book.getTitle()+"This is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ashekhawat8191@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        // prepare response
     return IssueBookResponse.builder()
             .bookName(savedBook.getTitle())
             .transactionStatus(savedTransaction.getTransactionStatus())
             .transactionTime(savedTransaction.getTransactionTime())
             .transactionNumber(savedTransaction.getTransactionNumber())
             .studentName(savedStudent.getName())
             .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
             .authorName(savedBook.getAuthor().getName())
             .build();

    }
}
