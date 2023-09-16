package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LibraryCardResponse {

    String cardNo;


    CardStatus cardStatus;


    Date issueDate;
}
