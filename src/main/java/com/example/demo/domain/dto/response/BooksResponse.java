package com.example.demo.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class BooksResponse extends BaseResponse {
    List<BookResponse> books;
}
