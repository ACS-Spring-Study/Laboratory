package com.example.demo.dto;

import com.example.demo.entity.BookCategory;
import com.example.demo.entity.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String title;
    private String isbn; //key?
    private String author;
    private BookCategory category;
    private BookStatus status;
}
