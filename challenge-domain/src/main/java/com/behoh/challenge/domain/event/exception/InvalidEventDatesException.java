package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class InvalidEventDatesException extends ChallengeException {
    public InvalidEventDatesException() {
        super("The end date and time must be after the start date and time!");
    }
}
