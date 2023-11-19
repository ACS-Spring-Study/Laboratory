package com.example.demo.controller;

import com.example.demo.domain.dto.request.RegisterBookDTO;
import com.example.demo.domain.dto.response.BaseResponse;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import com.example.demo.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    public BaseResponse registryBook(@RequestBody RegisterBookDTO registerBookDTO) {

      return bookService.registryBook(registerBookDTO);
    }

    @GetMapping("/books")
    public BaseResponse findAll() {
        return bookService.findAllBook();
    }

    @GetMapping("/book/{isbn}")
    public BaseResponse findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @GetMapping("/book/author")
    public BaseResponse findAllContainsAuthor(@RequestParam("name") String authorName) {
        return bookService.findAllContainsAuthor(authorName);
    }

    @GetMapping("/books/category")
    public BaseResponse findAllByCategory(@RequestParam("name") String categoryName) {
        return bookService.findAllByCategory(BookCategory.valueOf(categoryName));
    }
}
