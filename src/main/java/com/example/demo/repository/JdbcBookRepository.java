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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {
  private static final String DATABASE_URL = System.getenv("DATABASE_URL");
  private static Connection connection;
  private static Statement statement;
  private static PreparedStatement preparedStatement;

  private final Logger log = LoggerFactory.getLogger(getClass());

  private Book setBook(ResultSet resultSet) throws SQLException {
    Book book = new Book();

    String title = resultSet.getString("title");
    String isbn = resultSet.getString("isbn");
    String author = resultSet.getString("author");
    BookCategory bookCategory = BookCategory.valueOf(resultSet.getString("book_category"));
    BookStatus bookStatus = BookStatus.valueOf(resultSet.getString("book_status"));

    book.setTitle(title);
    book.setIsbn(isbn);
    book.setAuthor(author);
    book.setCategory(bookCategory);
    book.setStatus(bookStatus);

    return book;
  }

  public JdbcBookRepository(){
//    try {
//      connection = DriverManager.getConnection(DATABASE_URL);
//      statement = connection.createStatement();
//      log.info("DB Connection Succeed");
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
  }

  @Override
  public Book save(Book book) {
    String sql = "Insert into Book (title, isbn, author, book_category, book_status) values(?, ?, ?, ?, ?)";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, String.valueOf(book.getCategory()));
      preparedStatement.setString(5, String.valueOf(book.getStatus()));

      preparedStatement.executeUpdate();

      log.info(book.getTitle() +" 책 등록 성공");

      preparedStatement.close();

      return book;

    } catch (SQLException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Book findByISBN(String isbn) {

    String sql = "SELECT * from Book where isbn = ?";

    try {
      Book book = new Book();

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, isbn);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        book = setBook(resultSet);
        log.info(book.getTitle() + " 책 조회 성공");

      } else {
        log.info("책을 찾을 수 없습니다.");
      }

      return book;


    } catch (SQLException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    String sql = "SELECT isbn EXISTS (SELECT isbn FROM book WHERE isbn = ?)";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, isbn);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        log.info(isbn + " 은 이미 있는 책입니다.");
        return true;
      } else {
        log.info(isbn + "에 해당하는 책을 찾을 수 없습니다.");
        return false;
      }

    } catch (SQLException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();

    try {
      String sql = "Select * from Book";
      ResultSet resultset = statement.executeQuery(sql);

      while (resultset.next()) {
        Book book = setBook(resultset);

        log.info("모든 책 조회 성공");

        books.add(book);
      }

      resultset.close();
      statement.close();

      return books;

    } catch (SQLException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }
}
