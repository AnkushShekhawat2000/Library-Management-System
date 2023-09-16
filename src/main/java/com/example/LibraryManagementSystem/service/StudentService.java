package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.LibraryCardResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.transformer.LibraryCardTransformer;
import com.example.LibraryManagementSystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;



    public StudentResponse addStudent(StudentRequest studentRequest) {

        // create object using builder
        Student student =  StudentTransformer.StudentRequestToStudent(studentRequest);

        // give a library card
        LibraryCard card = LibraryCardTransformer.prepareLibraryCard(student);
        card.setStudent(student);
        student.setLibraryCard(card);
        Student savedStudent = studentRepository.save(student);

        // saved model to response dto
       return  StudentTransformer.StudentToStudentResponse(savedStudent);



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
