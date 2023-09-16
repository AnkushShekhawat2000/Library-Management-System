package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {
    String title;

    String noOfPAges;

    double cost;

    Genre genre;

    String authorName;

}
