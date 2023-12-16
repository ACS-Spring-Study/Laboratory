package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {

  Book save(Book book) throws SQLException;

  Book findByISBN(String isbn) throws SQLException;

  boolean existsByIsbn(String isbn) throws SQLException;

  List<Book> findAll() throws SQLException;
}
