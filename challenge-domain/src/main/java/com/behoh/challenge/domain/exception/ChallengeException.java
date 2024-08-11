package com.behoh.challenge.domain.exception;

public class ChallengeException extends RuntimeException {
    public ChallengeException() {}
    public ChallengeException(String message) {
        super(message);
    }
}
