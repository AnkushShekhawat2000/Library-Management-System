package com.example.LibraryManagementSystem;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_info")
public class Student {
    @Id
    int regNo;

    @Column(name = "student_name")
    String name;

    int age;
    String email;

    String random;

    @Enumerated(EnumType.STRING)
    Gender gender;
}
