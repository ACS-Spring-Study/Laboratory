package com.example.demo.domain.dto.response;

import com.example.demo.domain.entity.BookCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleBookResponse extends BaseResponse{
    String title;
    String isbn;
    String author;
    BookCategory category;
}
