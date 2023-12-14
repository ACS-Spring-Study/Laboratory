package com.example.demo.service;

import com.example.demo.domain.dto.request.BorrowBookDTO;
import com.example.demo.domain.dto.request.RegisterBookDTO;
import com.example.demo.domain.dto.request.ReturnBookDTO;
import com.example.demo.domain.dto.response.BookResponse;
import com.example.demo.domain.dto.response.BooksResponse;
import com.example.demo.domain.dto.response.OrderResponse;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import com.example.demo.domain.entity.BookStatus;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

  @Qualifier("jdbcBookRepository")
  @Autowired
  BookRepository bookRepository;

  public BooksResponse registryBook(RegisterBookDTO registerBookDTO) {
    BooksResponse response;

    try {
      Book newBook = Book
          .builder()
          .title(registerBookDTO.getTitle())
          .isbn(registerBookDTO.getIsbn())
          .author(registerBookDTO.getAuthor())
          .category(registerBookDTO.getCategory())
          .status(BookStatus.AVAILABLE)
          .build();

      Book savedBook = bookRepository.save(newBook);
      List<BookResponse> books = new ArrayList<>();

      books.add(
          BookResponse
              .builder()
              .title(savedBook.getTitle())
              .isbn(savedBook.getIsbn())
              .author(savedBook.getAuthor())
              .category(savedBook.getCategory())
              .status(savedBook.getStatus())
              .build()
      );

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException | SQLException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public BooksResponse findByIsbn(String isbn) {
    BooksResponse response;

    try {
      Book findBook = bookRepository.findByISBN(isbn);

      List<BookResponse> books = new ArrayList<>();

      books.add(
          BookResponse
              .builder()
              .title(findBook.getTitle())
              .isbn(findBook.getIsbn())
              .author(findBook.getAuthor())
              .category(findBook.getCategory())
              .status(findBook.getStatus())
              .build()
      );

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public BooksResponse findAllBook() {
    BooksResponse response;

    try {
      List<Book> findAllBook = bookRepository.findAll();
      List<BookResponse> books = new ArrayList<>();

      for (Book findBook : findAllBook) {
        BookResponse bookResponse = BookResponse
            .builder()
            .title(findBook.getTitle())
            .isbn(findBook.getIsbn())
            .author(findBook.getAuthor())
            .category(findBook.getCategory())
            .status(findBook.getStatus())
            .build();

        books.add(bookResponse);
      }

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public BooksResponse findAllContainsTitle(String title) {
    BooksResponse response;

    try {
      List<Book> findAllBook = bookRepository.findAll();
      List<BookResponse> books = new ArrayList<>();

      for (Book findBook : findAllBook) {
        if (findBook.getTitle().contains(title)) {
          BookResponse bookResponse = BookResponse
              .builder()
              .title(findBook.getTitle())
              .isbn(findBook.getIsbn())
              .author(findBook.getAuthor())
              .category(findBook.getCategory())
              .status(findBook.getStatus())
              .build();

          books.add(bookResponse);
        }
      }

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public BooksResponse findAllContainsAuthor(String authorName) {
    BooksResponse response;

    try {
      List<Book> findAllBook = bookRepository.findAll();
      List<BookResponse> books = new ArrayList<>();

      for (Book findBook : findAllBook) {
        if (findBook.getAuthor().contains(authorName)) {
          BookResponse bookResponse = BookResponse
              .builder()
              .title(findBook.getTitle())
              .isbn(findBook.getIsbn())
              .author(findBook.getAuthor())
              .category(findBook.getCategory())
              .status(findBook.getStatus())
              .build();

          books.add(bookResponse);
        }
      }

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public BooksResponse findAllByCategory(BookCategory category) {
    BooksResponse response;

    try {
      List<Book> findAllBook = bookRepository.findAll();
      List<BookResponse> books = new ArrayList<>();

      for (Book findBook : findAllBook) {
        if (findBook.getCategory().equals(category)) {
          BookResponse bookResponse = BookResponse
              .builder()
              .title(findBook.getTitle())
              .isbn(findBook.getIsbn())
              .author(findBook.getAuthor())
              .category(findBook.getCategory())
              .status(findBook.getStatus())
              .build();

          books.add(bookResponse);
        }

      }

      response = BooksResponse
          .builder()
          .message("Success")
          .books(books)
          .build();

    } catch (RuntimeException e) {
      response = BooksResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public OrderResponse borrowBook(BorrowBookDTO borrowBookDTO) {
    OrderResponse response;

    try {
      Book book = bookRepository.findByISBN(borrowBookDTO.getIsbn());

      if (book.getStatus() == BookStatus.AVAILABLE) {
        book.setStatus(BookStatus.BORROWING);

        response = OrderResponse
            .builder()
            .message("Borrowing book")
            .isbn(book.getIsbn())
            .status(book.getStatus())
            .build();
      } else {
        throw new RuntimeException("Book is not available!");
      }

    } catch (RuntimeException e) {
      response = OrderResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }

  public OrderResponse returnBook(ReturnBookDTO returnBookDTO) {
    OrderResponse response;

    try {
      Book book = bookRepository.findByISBN(returnBookDTO.getIsbn());

      if (book.getStatus() == BookStatus.AVAILABLE) {
        throw new RuntimeException("Book is available!");
      } else {
        book.setStatus(BookStatus.AVAILABLE);

        response = OrderResponse
            .builder()
            .message("Return book")
            .isbn(book.getIsbn())
            .status(book.getStatus())
            .build();
      }
    } catch (RuntimeException e) {
      response = OrderResponse
          .builder()
          .message(e.getMessage())
          .build();
    }

    return response;
  }
}
