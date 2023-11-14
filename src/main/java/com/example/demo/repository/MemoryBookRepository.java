package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryBookRepository implements BookRepository {

  private static HashMap<String, Book> memoryDB = new HashMap<>();

  @Override
  public Book save(Book book) {
    if (memoryDB.containsKey(book.getIsbn())) {
      throw new RuntimeException("Already Book!");
    }
    memoryDB.put(book.getIsbn(), book);
    return memoryDB.get(book.getIsbn());
  }

  @Override
  public Book findByISBN(String isbn) {
    if (!memoryDB.containsKey(isbn)) {
      throw new RuntimeException("Book not found!");
    }

    return memoryDB.get(isbn);
  }

  @Override
  public List<Book> findAll() {
    ArrayList<Book> result = new ArrayList<>();

    for(Book b : memoryDB.values()){
      result.add(b);
    }

    return result;
  }

  @Override
  public List<Book> findAllByContainsAuthor(String author) {
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
    List<Book> result = new ArrayList<>();

    for (Book b : memoryDB.values()) {
      if (b.getCategory() == category) {
        result.add(b);
      }
    }

    return result;
  }
}
