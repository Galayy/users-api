package com.itechart.container.spring.users.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public EntityNotFoundException(String message) {
        super(message);
        this.status = NOT_FOUND;
    }

}
