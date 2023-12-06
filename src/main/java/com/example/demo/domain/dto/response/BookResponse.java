package com.example.demo.domain.dto.response;

import com.example.demo.domain.entity.BookCategory;
import com.example.demo.domain.entity.BookStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@Builder
public class BookResponse {
  String title;
  String isbn;
  String author;
  BookCategory category;
  BookStatus status;
}
