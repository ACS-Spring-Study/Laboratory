package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository memoryBookRepository;

    public Book save(Book book){
        return memoryBookRepository.save(book);
    }

    public List<Book> findAll(){
        return memoryBookRepository.findAll();
    }

    public Book searchIsbn(String isbn){
        return memoryBookRepository.searchIsbn(isbn);
    }

    public List<Book> searchTitle(String title){
        return memoryBookRepository.searchTitle(title);
    }
    public List<Book> searchAuthor(String author){
        return memoryBookRepository.searchAuthor(author);
    }

    public List<Book> searchCategory(BookCategory category){
        return memoryBookRepository.searchCategory(category);
    }
    public Book borrow(String isbn){
        return memoryBookRepository.borrow(isbn);
    }

    public Book checkout(String isbn){
        return memoryBookRepository.checkout(isbn);
    }
}
