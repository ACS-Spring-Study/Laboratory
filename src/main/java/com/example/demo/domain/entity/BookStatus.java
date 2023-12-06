package com.example.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
	각각 대출 가능, 대출 중인 상태를 의미한다.
*/
@Getter
public enum BookStatus {
  AVAILABLE,
  BORROWING
}
