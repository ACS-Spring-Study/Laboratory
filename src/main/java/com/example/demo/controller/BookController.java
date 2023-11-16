package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.sevice.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jp_library")
public class BookController {

  @Autowired
  private BookService bookService;

  //도서관에 책을 등록하기
  @PostMapping("/book")
  public Book saveBook(@RequestBody Book book){
    return bookService.save(book);
  }

  //도서관이 보유한 전체 도서를 조회하기
  @GetMapping("/books")
  public List<Book> findAll(){
    return bookService.findAll();
  }

  //책의 ISBN으로 도서한권을 조회하기
  @GetMapping("book/isbn")
  @ResponseBody
  public List<Book> findByIsbn(@RequestBody Book book){
    return bookService.findByIsbn(book.getIsbn());
  }

  //책을 도서명으로 조회하기
  @GetMapping("book/title")
  @ResponseBody
  public List<Book> findByTitle(@RequestBody Book book){
    return bookService.findByTitle(book.getTitle());
  }

  //책을 저자명으로 조회하기
  @GetMapping("book/author")
  @ResponseBody
  public List<Book> findByAuthor(@RequestBody Book book){
    return bookService.findByAuthor(book.getAuthor());
  }
  //책을 분류별로 조회하기
  @GetMapping("book/category")
  @ResponseBody
  public List<Book> findByCategory(@RequestBody Book book){
    return bookService.findByCategory(book.getCategory());
  }

  //책을 한권 대여하기

  //대여한 책을 반납하기

}
