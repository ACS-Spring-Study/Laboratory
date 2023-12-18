package com.example.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="title")
  String title;

  @Column(name="isbn")
  String isbn;

  @Column(name="author")
  String author;

  @Enumerated(EnumType.STRING)
  @Column(name="book_category")
  BookCategory category;

  @Enumerated(EnumType.STRING)
  @Column(name="book_status")
  BookStatus status;
}