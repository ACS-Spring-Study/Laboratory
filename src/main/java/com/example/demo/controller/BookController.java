package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ty_library")
public class BookController {

    @Autowired
    private BookService bookService;

    //도서관에 책을 등록하기
    @PostMapping("/book")
    public Book save(@RequestBody BookDTO dto){
        Book saved = bookService.save(dto);
        return saved;
    }

    //도서관이 보유한 전체 도서를 조회하기
    @GetMapping("/books")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    //책의 ISBN으로 도서 한 권을 조회하기
    @GetMapping("/book/{isbn}")
    public Book findIsbn(@PathVariable String isbn){
        return bookService.searchIsbn(isbn);
    }

    //책을 도서명으로 조회하기
    @GetMapping("/book/title/{title}")
    public Book findTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }
//    책을 저자명으로 조회하기
    @GetMapping("/book/author/{author}")
    public Book findAuthor(@PathVariable String author){
        return bookService.searchAuthor(author);
    }

    //책을 분류(Category)별로 조회하기
    @GetMapping("/book/category/{category}")
    public Book findCategory(@PathVariable BookCategory category){
        return bookService.searchCategory(category);
    }
    //책을 한 권 대여하기
    @PatchMapping("/book/borrow/{isbn}")
    public Book borrow(@PathVariable String isbn){

        return bookService.borrow(isbn);
    }

    //대여한 책을 반납하기
    @PatchMapping("/book/checkout/{isbn}")
    public Book checkout(@PathVariable String isbn){
        return bookService.checkout(isbn);
    }
}
