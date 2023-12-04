package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.entity.BookStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryBookRepository implements BookRepository{
  HashMap<String, Book> memoryDB = new HashMap<>();

  //도서관에 책을 등록하기
  @Override
  public Book save(Book book){
    memoryDB.put(book.getIsbn(), book);
    return book;
  }

  //도서관이 보유한 전체 도서를 조회하기
  @Override
  public List<Book> findAll(){
    return new ArrayList<>(memoryDB.values());
  }

  //책의 ISBN으로 도서한권을 조회하기
  @Override
  public Book findByISBN(String isbn){
    System.out.println(memoryDB.get(isbn));
    return memoryDB.get(isbn);
  }

  //책을 도서명으로 조회하기
  @Override
  public List<Book> findByTitle(String title){
    return memoryDB.values().stream()
        .filter(book -> book.getTitle().equals(title))
        .collect(Collectors.toList());
  }

  //책을 저자명으로 조회하기
  @Override
  public List<Book> findByAuthor(String author){
    return memoryDB.values().stream()
        .filter(book -> book.getAuthor().equals(author))
        .collect(Collectors.toList());
  }

  //책을 분류별로 조회하기
  @Override
  public List<Book> findByCategory(BookCategory category){
    return memoryDB.values().stream()
        .filter(book -> book.getCategory().equals(category))
        .collect(Collectors.toList());
  }


  // 책 대여하기
  @Override
  public Book borrowBook(String isbn){
    Book borrowBk = findByISBN(isbn);
    if (borrowBk.getStatus()==BookStatus.AVAILABLE){
      borrowBk.setStatus(BookStatus.BORROWING);
    }
  return borrowBk;
  }

  //책 반납하기
  @Override
  public Book returnBook(String isbn){
    Book borrowBk = findByISBN(isbn);
    if (borrowBk.getStatus()==BookStatus.BORROWING){
      borrowBk.setStatus(BookStatus.AVAILABLE);
    }
    return borrowBk;
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    return false;
  }
}
