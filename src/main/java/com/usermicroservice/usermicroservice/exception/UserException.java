package com.usermicroservice.usermicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class UserException {
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;
}
