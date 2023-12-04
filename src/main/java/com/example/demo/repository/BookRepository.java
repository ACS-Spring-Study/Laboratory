package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository {
  Book saveBook(Book book);
  List<Book> findAll();
  Book findByIsbn(String isbn);
  List<Book> findByTitle(String title);
  List<Book> findByAuthor(String author);
  List<Book> findByCategory(BookCategory category);
  Book borrowBook(String isbn);
  Book returnBook(String isbn);
}
