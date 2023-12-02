package com.example.demo.entity;

import com.example.demo.dto.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /*
	책의 제목, ISBN, 저자, 책의 분류, 책의 상태를 멤버로 갖는다.
    */

    @Id
    private String isbn; //key?
    @Column
    private String title;
    @Column
    private String author;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BookCategory category;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;


}
