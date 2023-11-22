package com.example.demo.controller;

import com.example.demo.domain.dto.request.BorrowBookDTO;
import com.example.demo.domain.dto.request.RegisterBookDTO;
import com.example.demo.domain.dto.request.ReturnBookDTO;
import com.example.demo.domain.dto.response.BaseResponse;
import com.example.demo.domain.dto.response.BookResponse;
import com.example.demo.domain.dto.response.BooksResponse;
import com.example.demo.domain.dto.response.OrderResponse;
import com.example.demo.domain.entity.BookCategory;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sj_library")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public BooksResponse registryBook(@RequestBody RegisterBookDTO registerBookDTO) {
    return bookService.registryBook(registerBookDTO);
  }

  @GetMapping("/books")
  public BooksResponse findAll() {
    return bookService.findAllBook();
  }

  @GetMapping("/book/{isbn}")
  public BooksResponse findByIsbn(@PathVariable String isbn) {
    return bookService.findByIsbn(isbn);
  }

  @GetMapping("/books/title")
  public BooksResponse findAllContainsTitle(@RequestParam("contains") String title) {
    return bookService.findAllContainsTitle(title);
  }

  @GetMapping("/book/authorName")
  public BooksResponse findAllContainsAuthor(@RequestParam("contains") String authorName) {
    return bookService.findAllContainsAuthor(authorName);
  }

  @GetMapping("/books/category")
  public BooksResponse findAllByCategory(@RequestParam("name") String categoryName) {
    return bookService.findAllByCategory(BookCategory.valueOf(categoryName));
  }

  @PatchMapping(value = "/book/borrow", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public OrderResponse borrowBook(@RequestBody BorrowBookDTO borrowBookDTO) {
    return bookService.borrowBook(borrowBookDTO);
  }

  @PatchMapping(value = "/book/return", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public OrderResponse returnBook(@RequestBody ReturnBookDTO returnBookDTO) {
    return bookService.returnBook(returnBookDTO);
  }
}
