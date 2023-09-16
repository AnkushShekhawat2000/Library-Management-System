package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.Student;

public class StudentTransformer {

    public static Student StudentRequestToStudent(StudentRequest studentRequest)
    {
//        Student student = Student.builder()
        return  Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();

    }


//    public static StudentResponse StudentToStudentResponse(Student student)
//    {
//
//    }
}
