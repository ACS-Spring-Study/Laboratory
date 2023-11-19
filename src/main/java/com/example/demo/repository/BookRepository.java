package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import java.util.List;

public interface BookRepository {
  Book save(Book book);
  Book findByISBN(String isbn);
  List<Book> findAll();
  List<Book> findAllByContainsAuthor(String author);
  List<Book> findAllByCategory(BookCategory category);
}
