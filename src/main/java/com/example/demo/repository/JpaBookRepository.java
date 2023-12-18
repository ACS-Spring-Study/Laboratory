package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {
	Book findByIsbn(String isbn);
	List<Book> findByTitleContaining(String title);
	List<Book> findByAuthorContaining(String author);
	List<Book> findByCategory(BookCategory category);
}