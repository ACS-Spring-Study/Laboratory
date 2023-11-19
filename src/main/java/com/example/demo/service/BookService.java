package com.example.demo.service;

import com.example.demo.domain.dto.RegisterBookDTO;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import com.example.demo.domain.entity.BookStatus;
import com.example.demo.repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository memoryBookRepository;

    public Book registryBook(RegisterBookDTO registerBookDTO) {
        Book newBook = Book.builder()
                .title(registerBookDTO.getTitle())
                .isbn(registerBookDTO.getIsbn())
                .author(registerBookDTO.getAuthor())
                .category(registerBookDTO.getCategory())
                .status(BookStatus.AVAILABLE)
                .build();

        return memoryBookRepository.save(newBook);
    }

    public Book findByIsbn(String isbn) {
        return memoryBookRepository.findByISBN(isbn);
    }

    public List<Book> findAllBook() {
        return memoryBookRepository.findAll();
    }

    public List<Book> findAllContainsAuthor(String authorName) {
        return memoryBookRepository.findAllByContainsAuthor(authorName);
    }

    public List<Book> findAllByCategory(BookCategory category) {
        return memoryBookRepository.findAllByCategory(category);
    }
}
