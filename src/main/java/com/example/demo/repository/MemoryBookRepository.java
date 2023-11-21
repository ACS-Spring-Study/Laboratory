package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryBookRepository implements BookRepository {

    private static HashMap<String, Book> memoryDB = new HashMap<>();

    @Override
    public Book save(Book book) {
        if (this.existsByIsbn(book.getIsbn())) {
          throw new RuntimeException("Book is already registered!");
        }

        memoryDB.put(book.getIsbn(), book);
        return memoryDB.get(book.getIsbn());
    }

    @Override
    public Book findByISBN(String isbn) {
        if (!this.existsByIsbn(isbn)) {
            throw new RuntimeException("Book not found!");
        }

        return memoryDB.get(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return memoryDB.containsKey(isbn);
    }

    @Override
    public List<Book> findAll() {
        if(memoryDB.size() <= 0){
            throw new RuntimeException("Library is empty!");
        }

        ArrayList<Book> result = new ArrayList<>();

        for (Book b : memoryDB.values()) {
            result.add(b);
        }

        return result;
    }

    @Override
    public List<Book> findAllByContainsAuthor(String author) {
        if(memoryDB.size() <= 0){
            throw new RuntimeException("Library is empty!");
        }

        List<Book> result = new ArrayList<>();

        for (Book b : memoryDB.values()) {
            if (b.getAuthor().contains(author)) {
                result.add(b);
            }
        }

        return result;
    }

    @Override
    public List<Book> findAllByCategory(BookCategory category) {
        if(memoryDB.size() <= 0){
            throw new RuntimeException("Library is empty!");
        }

        List<Book> result = new ArrayList<>();

        for (Book b : memoryDB.values()) {
            if (b.getCategory().name().equals(category.name())) {
                result.add(b);
            }
        }

        return result;
    }
}
