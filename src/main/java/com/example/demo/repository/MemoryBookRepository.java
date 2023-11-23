package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.entity.BookStatus;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryBookRepository implements BookRepository{
    HashMap<Integer, Book> memoryDB = new HashMap<>();

    public static int id = 0;
    @Override
    public Book save(Book book){
        memoryDB.put(++id,book); //임시로 id 사용 isbn이 key값으로 변경될 예정
        return book;
    }

    @Override
    public ArrayList<Book> findAll(){
        return new ArrayList<>(memoryDB.values());
    }

    @Override
    public Book searchIsbn(String isbn){
        return memoryDB.values()
                .stream()//효과적인 데이터 처리를 위한 방식
                .filter(book -> isbn.equals(book.getIsbn())) //book에 isbn이 일치하는지 확인
                .findFirst()//isbn이 일치하는 첫번째 도서 반환
                .orElse(null); //없다면 null 반환
    }

    @Override
    public List<Book> searchTitle(String title){
        return memoryDB.values()
                .stream()
                .filter(book -> title.equals(book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchAuthor(String author){
        return memoryDB.values()
                .stream()
                .filter(book -> author.equals(book.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchCategory(BookCategory category){
        return memoryDB.values()
                .stream()
                .filter(book -> category.equals(book.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public Book borrow(String isbn){
        Book book = searchIsbn(isbn);
        book.setStatus(BookStatus.BORRAWING);
        return book;

    }

    @Override
    public Book checkout(String isbn){
        Book book = searchIsbn(isbn);
        book.setStatus(BookStatus.AVAILABLE);
        return book;
    }
}