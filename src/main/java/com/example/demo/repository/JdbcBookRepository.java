package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCategory;
import com.example.demo.domain.entity.BookStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {

  private static final String DATABASE_URL = System.getenv("DATABASE_URL");
  private static Connection connection;
  private static Statement statement;
  private static PreparedStatement preparedStatement;
  private static ResultSet resultSet;

  public JdbcBookRepository() {
    try {
      connection = DriverManager.getConnection(DATABASE_URL);
      statement = connection.createStatement();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book save(Book book) {
    try {
      String query = "INSERT INTO book (title, isbn, author, book_category, book_status) VALUES (?, ?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, book.getCategory().toString());

      preparedStatement.executeUpdate();

      System.out.println("등록 성공 !!!");
      preparedStatement.close();
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return book;
  }

  @Override
  public Book findByISBN(String isbn) {
    try {
      String query = "SELECT * FROM book WHERE isbn = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, isbn);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return Book.builder()
            .title(resultSet.getString("title"))
            .isbn(resultSet.getString("isbn"))
            .author(resultSet.getString("author"))
            .category(BookCategory.valueOf(resultSet.getString("book_category")))
            .status(BookStatus.valueOf(resultSet.getString("book_status")))
            .build();
      }
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return null;
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    boolean flag = false;
    try {
      String query = "SELECT EXISTS(SELECT 1 FROM your_books_table WHERE isbn = ?) AS bookExists";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, isbn);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        flag = resultSet.getBoolean("bookExists");
      }
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return flag;
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();
    try {
      String query = "SELECT * FROM book";
      resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        String title = resultSet.getString("title");
        String isbn = resultSet.getString("isbn");
        String author = resultSet.getString("author");
        BookCategory category = BookCategory.valueOf(resultSet.getString("book_category"));
        BookStatus status = BookStatus.valueOf(resultSet.getString("book_status"));
        Book book = new Book(title, isbn, author, category, status);
        books.add(book);
      }
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return books;
  }

}
