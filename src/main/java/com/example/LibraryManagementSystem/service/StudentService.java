package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.LibraryCardResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
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

        // convert request dto to model
   // take request studentrequest and set student obj
//        Student student = new Student();
//        student.setName(studentRequest.getName());
//        student.setAge(studentRequest.getAge());
//        student.setGender(studentRequest.getGender());
//        student.setEmail(studentRequest.getEmail());


        // create object using builder
    Student student =  StudentTransformer.StudentRequestToStudent(studentRequest);


        // give a library card
//        LibraryCard libraryCard = new LibraryCard();
//        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
//        libraryCard.setCardStatus(CardStatus.ACTIVE);
//        libraryCard.setStudent(student);
//
//        student.setLibraryCard(libraryCard);

       LibraryCard libraryCard = LibraryCard.builder()
                                .cardNo(String.valueOf(UUID.randomUUID()))
                                .cardStatus(CardStatus.ACTIVE)
                                .student(student)
                                .build();

        student.setLibraryCard(libraryCard);



        Student savedStudent = studentRepository.save(student);

       // saved model to response dto
//
//        StudentResponse studentResponse = new StudentResponse();
//        studentResponse.setName(savedStudent.getName());
//        studentResponse.setEmail(savedStudent.getEmail());
//        studentResponse.setMessage("You have been registered");

        // create studentresponse using builder

        StudentResponse studentResponse = StudentResponse.builder()
                .name(savedStudent.getName())
                .email(savedStudent.getEmail())
                .message("You have been registered")
                .build();

//        LibraryCardResponse cardResponse = new LibraryCardResponse();
//        cardResponse.setCardNo(savedStudent.getLibraryCard().getCardNo());
//        cardResponse.setIssueDate(savedStudent.getLibraryCard().getIssueDate());
//        cardResponse.setCardStatus(savedStudent.getLibraryCard().getCardStatus());
//        studentResponse.setLibraryCardResponse(cardResponse);

        LibraryCardResponse cardResponse = LibraryCardResponse.builder()
                                            .cardNo(savedStudent.getLibraryCard().getCardNo())
                                            .cardStatus(savedStudent.getLibraryCard().getCardStatus())
                                            .issueDate(savedStudent.getLibraryCard().getIssueDate())
                                            .build();

        studentResponse.setLibraryCardResponse(cardResponse);

        return studentResponse;
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
