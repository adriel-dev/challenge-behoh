package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class RegistrationNotAllowedException extends ChallengeException {
    public RegistrationNotAllowedException(Long userId) {
        super("User with ID ["+userId+"] is already registered or already has a reserved registration for the event!");
    }
}
