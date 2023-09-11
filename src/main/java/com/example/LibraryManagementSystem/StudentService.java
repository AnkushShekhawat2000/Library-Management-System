package com.example.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(Student student) {

        Student savedStudent = studentRepository.save(student);

        return savedStudent;

    }

    public Student getStudent(int regNo) {

        Optional<Student> studentOptionl = studentRepository.findById(regNo);
        if (studentOptionl.isPresent())
        {
            return studentOptionl.get();
        }
         return null;
    }
}
