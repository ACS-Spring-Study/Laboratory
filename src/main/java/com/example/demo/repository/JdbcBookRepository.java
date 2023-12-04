package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
    return null;
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

  @Override
  public List<Book> findByTitle(String title){
    return null;
  }
  @Override
  public List<Book> findByAuthor(String author){
    return null;
  }

  @Override
  public List<Book> findByCategory(BookCategory category){
    return null;
  }

  @Override
  public Book borrowBook(String isbn){
    return null;
  }
  @Override
  public Book returnBook(String isbn){
    return null;
  }
}