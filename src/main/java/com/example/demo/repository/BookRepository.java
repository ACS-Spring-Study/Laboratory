package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {


    @Override
    Book save(Book book);
    ArrayList<Book> findAll();

//일반적인 작성방법
//    Book findByIsbn(String isbn);
//  orElse(null)이라는 예외처리를 실행하기 위해서는 Optional을 사용해야 한다.
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
    Optional<Book> findByCategory(BookCategory category);





}
