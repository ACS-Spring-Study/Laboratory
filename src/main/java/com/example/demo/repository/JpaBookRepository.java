package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, String> {
  Book findByIsbn(String isbn);
  List<Book> findByTitleContains(String title);
  List<Book> findByAuthorContains(String authorName);
  List<Book> findAllByCategory(BookCategory category);
}