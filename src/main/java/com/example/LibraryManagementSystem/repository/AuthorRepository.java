package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


}
