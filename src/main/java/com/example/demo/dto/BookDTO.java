package com.example.demo.dto;

import com.example.demo.entity.Book;
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

    private String isbn; //key?
    private String title;
    private String author;
    private BookCategory category;
    private BookStatus status;

    public Book toEntity(){
        return new Book(isbn, title, author, category, status);
    }
}
