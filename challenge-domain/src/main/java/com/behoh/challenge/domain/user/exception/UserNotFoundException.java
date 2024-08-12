package com.behoh.challenge.domain.user.exception;

import com.behoh.challenge.domain.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long userId) {
        super("No User with ID ["+userId+"] was found!");
    }
}
