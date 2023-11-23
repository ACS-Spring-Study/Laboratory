package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository {


    Book save(Book book);
    ArrayList<Book> findAll();
    Book searchIsbn(String isbn);
    List<Book> searchTitle(String title);
    List<Book> searchAuthor(String author);
    List<Book> searchCategory(BookCategory category);
    Book borrow(String isbn);
    Book checkout(String isbn);



}
