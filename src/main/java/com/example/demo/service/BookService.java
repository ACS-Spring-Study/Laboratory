package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  BookRepository memoryBookRepository;

  public Book registryBook(Book book){
    return memoryBookRepository.save(book);
  }

  public Book findByIsbn(String isbn){
    return memoryBookRepository.findByISBN(isbn);
  }

  public List<Book> findAllBook(){
    return memoryBookRepository.findAll();
  }

  public List<Book> findAllContainsAuthor(String authorName){
    return memoryBookRepository.findAllByContainsAuthor(authorName);
  }

  public List<Book> findAllByCategory(BookCategory category){
    return memoryBookRepository.findAllByCategory(category);
  }
}
