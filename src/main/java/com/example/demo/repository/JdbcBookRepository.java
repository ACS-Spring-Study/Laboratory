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

  private static final Logger logger = LoggerFactory.getLogger(JdbcBookRepository.class);

  private Connection getConnection() throws SQLException{
    return DriverManager.getConnection(DATABASE_URL);
  }

  private Book setBook(ResultSet resultSet) throws SQLException {
    Book book = new Book();

    book.setTitle(resultSet.getString("title"));
    book.setIsbn(resultSet.getString("isbn"));
    book.setAuthor(resultSet.getString("author"));
    book.setCategory(BookCategory.valueOf(resultSet.getString("book_category")));
    book.setStatus(BookStatus.valueOf(resultSet.getString("book_status")));
    return book;
  }

  @Override
  public Book save(Book book) throws SQLException {
    String sql = "Insert into Book values(?, ?, ?, ?, ?)";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getIsbn());
      preparedStatement.setString(3, book.getAuthor());
      preparedStatement.setString(4, String.valueOf(book.getCategory()));
      preparedStatement.setString(5, String.valueOf(book.getStatus()));

      preparedStatement.executeUpdate();


      logger.info("{} 책 등록 완료", book.getTitle());

    } catch (SQLException e) {
      logger.error("책 등록 오류 발생",e);
      throw new RuntimeException("책 등록 오류 발생",e);
    } finally {
      preparedStatement.close();
    }
      return book;
  }

  @Override
  public Book findByISBN(String isbn) {

    String sql = "SELECT * from Book where isbn = ?";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, isbn);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {

        Book book = setBook(resultSet);

        logger.info("{} 책 조회 완료", book.getTitle());

        return book;

      } else {
        logger.info("책을 찾을 수 없습니다.");
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;

  }

  @Override
  public boolean existsByIsbn(String isbn) {
    String sql = "SELECT isbn from Book where isbn = ?";

    try {
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, isbn);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        logger.info("{} 은 이미 존재하는 책입니다.", isbn);
        return true;
      } else {
        logger.info("{}에 해당하는 책을 찾을 수 없습니다.", isbn);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();

    try {
      String sql = "Select * from Book";
      ResultSet resultset = statement.executeQuery(sql);

      while (resultset.next()) {
        Book book = setBook(resultset);

        logger.info("모든 책 조회 완료");

        books.add(book);
      }

      resultset.close();
      statement.close();

      return books;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}