package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.entity.BookStatus;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository BookRepository;

    public Book save(BookDTO dto){
        Book book = dto.toEntity();
        return BookRepository.save(book);
    }

    public List<Book> findAll(){
        return BookRepository.findAll();
    }

    public Book searchIsbn(String isbn){
//        return BookRepository.findByIsbn(isbn);
        return BookRepository.findByIsbn(isbn).orElse(null);
    }

    public Book findByTitle(String title){
        return BookRepository.findByTitle(title).orElse(null);
    }
    public Book searchAuthor(String author){
        return BookRepository.findByAuthor(author).orElse(null);
    }

    public Book searchCategory(BookCategory category){
        return BookRepository.findByCategory(category).orElse(null);
    }
    public Book borrow(String isbn){
        //isbn으로 빌리는 책의 데이터를 찾기
        Book target = searchIsbn(isbn);
        //찾은 데이터의 정보를 수정
        target.setStatus(BookStatus.BORRAWING); //setter를 지양하게되면 직접 set Status를 작성해야 할 것
        //저장
        return BookRepository.save(target);
    }

    public Book checkout(String isbn){
        Book target = searchIsbn(isbn);
        target.setStatus(BookStatus.AVAILABLE);
        return BookRepository.save(target);
    }
}
