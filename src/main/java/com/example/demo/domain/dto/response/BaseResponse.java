package com.example.demo.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseResponse {
    String message;
}
