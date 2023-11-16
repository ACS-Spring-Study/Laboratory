package com.example.demo.sevice;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  BookRepository memoryBookRepository;

  //도서관에 책을 등록하기
  public Book save(Book book){
    return memoryBookRepository.saveBook(book);
  }

  //도서관이 보유한 전체 도서를 조회하기
  public List<Book> findAll(){
    return memoryBookRepository.findAll();
  }

  //책의 ISBN으로 도서한권을 조회하기
  public List<Book> findByIsbn(String isbn){
    return memoryBookRepository.findByIsbn(isbn);
  }

  //책을 도서명으로 조회하기
  public List<Book> findByTitle(String title){
    return memoryBookRepository.findByTitle(title);
  }

  //책을 저자명으로 조회하기
  public List<Book> findByAuthor(String author){
    return memoryBookRepository.findByAuthor(author);
  }

  //책을 분류별로 조회하기
  public List<Book> findByCategory(BookCategory category){
    return memoryBookRepository.findByCategory(category);
  }

  public List<Book> borrowBook(String isbn){
    return memoryBookRepository.borrowBook(isbn);
  }

  public List<Book> returnBook(String isbn){
    return memoryBookRepository.returnBook(isbn);
  }
}
