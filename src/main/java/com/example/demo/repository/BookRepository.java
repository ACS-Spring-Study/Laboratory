package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import java.util.List;

public interface BookRepository {
  Book save(Book book);
  Book findByISBN(String isbn);
  boolean existsByIsbn(String isbn);
  List<Book> findAll();
}
