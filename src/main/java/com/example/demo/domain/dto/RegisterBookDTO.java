package com.example.demo.domain.dto;

import com.example.demo.domain.entity.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBookDTO {
    String title;
    String isbn;
    String author;
    BookCategory category;
}
