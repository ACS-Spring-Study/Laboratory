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
  private static ResultSet rs;

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
      String sql = "insert into book (title, isbn, author, book_category, book_status) VALUES (?, ?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setNString(1, book.getTitle());
      preparedStatement.setNString(2, book.getIsbn());
      preparedStatement.setNString(3, book.getAuthor());
      preparedStatement.setNString(4, book.getCategory().name());
      preparedStatement.setNString(5, book.getStatus().name());

      preparedStatement.executeUpdate();
      System.out.println("책 등록");
    } catch (SQLException e) {
      System.out.println("등록 실패");
    }
    return book;
  }

  @Override
  public Book findByISBN(String isbn) {
    try {
      String sql = "select * from book where isbn=?";
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, isbn);
      rs = preparedStatement.executeQuery();
      if(rs.next()){
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(BookCategory.valueOf(rs.getString("book_category")));
        book.setStatus(BookStatus.valueOf(rs.getString("book_status")));
        return book;
      }
    }catch(SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    return false;
  }

  @Override
  public List<Book> findAll(){
    List<Book> Books = new ArrayList<>();
    try {
      String sql = "select * from book";

      preparedStatement = connection.prepareStatement(sql);
      rs = preparedStatement.executeQuery();
      while(rs.next()){
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(BookCategory.valueOf(rs.getString("book_category")));
        book.setStatus(BookStatus.valueOf(rs.getString("book_status")));
        Books.add(book);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return Books;
  }

  @Override
  public List<Book> findByTitle(String title){
    List<Book> Books = new ArrayList<>();
    try {
      String sql = "select * from book where title=?";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, title);
      rs = preparedStatement.executeQuery();

      while(rs.next()){
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(BookCategory.valueOf(rs.getString("book_category")));
        book.setStatus(BookStatus.valueOf(rs.getString("book_status")));
        Books.add(book);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return Books;
  }
  @Override
  public List<Book> findByAuthor(String author){

    List<Book> Books = new ArrayList<>();
    try {
      String sql = "select * from book where author=?";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, author);
      rs = preparedStatement.executeQuery();

      while(rs.next()){
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(BookCategory.valueOf(rs.getString("book_category")));
        book.setStatus(BookStatus.valueOf(rs.getString("book_status")));
        Books.add(book);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return Books;
  }

  @Override
  public List<Book> findByCategory(BookCategory category){
    List<Book> Books = new ArrayList<>();
    try {
      String sql = "select * from book where category=?";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, String.valueOf(category));
      rs = preparedStatement.executeQuery();

      while(rs.next()){
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(BookCategory.valueOf(rs.getString("book_category")));
        book.setStatus(BookStatus.valueOf(rs.getString("book_status")));
        Books.add(book);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return Books;
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