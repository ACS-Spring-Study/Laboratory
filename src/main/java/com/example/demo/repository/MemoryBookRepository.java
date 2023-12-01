package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;

import com.example.demo.domain.entity.BookStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("MemoryBookRepository")
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
    if (memoryDB.size() <= 0) {
      throw new RuntimeException("Library is empty!");
    }

    ArrayList<Book> result = new ArrayList<>();

    for (Book b : memoryDB.values()) {
      result.add(b);
    }

    return result;
  }
}
