package com.itechart.container.spring.users.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class EntityAlreadyProcessedException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public EntityAlreadyProcessedException(String message) {
        super(message);
        this.status = UNPROCESSABLE_ENTITY;
    }

}
