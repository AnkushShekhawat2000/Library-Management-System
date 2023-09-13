package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;



    public String addStudent(Student student) {

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);



        Student savedStudent = studentRepository.save(student);

        return "Student saved susscessfully";

    }

    public Student getStudent(int regNo) {

        Optional<Student> studentOptionl = studentRepository.findById(regNo);
        if (studentOptionl.isPresent())
        {
            return studentOptionl.get();
        }
         return null;
    }

    public List<String> getAllMales() {

        List<String> names =new ArrayList<>();
      List<Student> students = studentRepository.findByGender(Gender.MALE);
      for(Student s : students)
      {
          names.add(s.getName());
      }
   return names;
    }
}
