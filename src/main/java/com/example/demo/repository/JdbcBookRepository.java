package com.example.demo.repository;

import com.example.demo.domain.entity.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {
  private static final String DATABASE_URL = System.getenv("DATABASE_URL");
  private static Connection connection;
  private static Statement statement;
  private static PreparedStatement preparedStatement;

  public JdbcBookRepository(){
    try {
      connection = DriverManager.getConnection(DATABASE_URL);
      statement = connection.createStatement();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book save(Book book) {
    try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
      String query = "INSERT INTO book (title, isbn, author, book_category, book_status) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, book.getCategory().toString());
      preparedStatement.setString(5, book.getStatus().toString());

      preparedStatement.executeUpdate();

      System.out.println("등록 성공 !!!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return book;
  }

  @Override
  public Book findByISBN(String isbn) {
    return null;
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    return false;
  }

  @Override
  public List<Book> findAll() {
    return null;
  }
}
