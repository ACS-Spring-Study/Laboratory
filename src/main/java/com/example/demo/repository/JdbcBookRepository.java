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
      System.out.println("DB Connection Succeed");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book save(Book book) {
    String sql = "Insert into Book values(NULL, ?, ?, ?, ?, ?)";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, String.valueOf(book.getCategory()));
      preparedStatement.setString(5, String.valueOf(book.getStatus()));

      preparedStatement.executeUpdate();

      System.out.println(book.getTitle() +" 책 등록 성공");

      preparedStatement.close();
//      connection.close();

      return book;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
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
    List<Book> books = new ArrayList<>();

    try {
      String sql = "Select * from Book";
      ResultSet resultset = statement.executeQuery(sql);

      while (resultset.next()) {
        Book book = new Book();
        book.setTitle(resultset.getString("title"));
        book.setIsbn(resultset.getString("isbn"));
        book.setAuthor(resultset.getString("author"));
        book.setCategory(BookCategory.valueOf(resultset.getString("book_category")));
        book.setStatus(BookStatus.valueOf(resultset.getString("book_status")));

        System.out.println("모든 책 조회 성공");

        books.add(book);
      }

      resultset.close();
      statement.close();
//      connection.close();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return books;
  }
}
