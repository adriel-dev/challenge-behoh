package com.behoh.domain.user.exception;

import com.behoh.domain.exception.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(UUID userId) {
        super("No User with ID ["+userId+"] was found!");
    }
}
