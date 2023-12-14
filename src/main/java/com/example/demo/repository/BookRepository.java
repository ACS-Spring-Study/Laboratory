package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface BookRepository {

  Book save(Book book) throws SQLException;

  Book findByISBN(String isbn);

  boolean existsByIsbn(String isbn);

  List<Book> findAll();
}
