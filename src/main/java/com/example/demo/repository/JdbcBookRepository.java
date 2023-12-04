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
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {
  private static final String DATABASE_URL = System.getenv("DATABASE_URL");
  private static Connection connection;
  private static Statement statement;
  private static PreparedStatement preparedStatement;
  ResultSet resultSet;
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
    try {
      String query = "INSERT INTO book (title, isbn, author, book_category, book_status) VALUES (?, ?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, book.getCategory().toString());

      preparedStatement.executeUpdate();

      System.out.println("등록 성공 !!!");
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return book;
  }

  @Override
  public Book findByISBN(String isbn) {
    try{
      preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE isbn = ?");
      preparedStatement.setString(1, isbn);
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
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
	  return false;
  }

  @Override
  public List<Book> findAll() {
    return null;
  }
}
