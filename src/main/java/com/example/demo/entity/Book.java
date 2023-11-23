package com.example.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /*
	책의 제목, ISBN, 저자, 책의 분류, 책의 상태를 멤버로 갖는다.
    */

    String title;
    @Id
    String isbn; //key?
    String author;
    BookCategory category;
    BookStatus status;

}
