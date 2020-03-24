package com.itechart.container.spring.users.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ForbiddenAccessException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public ForbiddenAccessException(String message) {
        super(message);
        this.status = FORBIDDEN;
    }

}
