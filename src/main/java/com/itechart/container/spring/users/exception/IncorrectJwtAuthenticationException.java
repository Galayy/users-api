package com.itechart.container.spring.users.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class IncorrectJwtAuthenticationException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public IncorrectJwtAuthenticationException(String message) {
        super(message);
        this.status = UNAUTHORIZED;
    }

}
