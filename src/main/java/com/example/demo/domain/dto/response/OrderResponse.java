package com.example.demo.domain.dto.response;

import com.example.demo.domain.entity.BookStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class OrderResponse extends BaseResponse {
  String isbn;
  BookStatus status;
}
