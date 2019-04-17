package com.eventservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadEntityException extends Exception {

    public BadEntityException(String message) {
        super(message);
    }


}
