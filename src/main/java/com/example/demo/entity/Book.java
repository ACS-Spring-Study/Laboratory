package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {

  String title;
  String isbn;
  String author;
  BookCategory category;
  BookStatus status;
}