package com.example.bookProject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
}
