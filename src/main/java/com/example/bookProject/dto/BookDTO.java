package com.example.bookProject.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Integer id;

    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?" +
            "(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|" +
            "97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)" +
            "(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
            message = "ISBN must have the correct format")
    private String isbn;

    @NotEmpty(message = "You should write the title of the book!")
    private String title;

    @NotEmpty(message = "You should write the author of the book!")
    private String author;
}
