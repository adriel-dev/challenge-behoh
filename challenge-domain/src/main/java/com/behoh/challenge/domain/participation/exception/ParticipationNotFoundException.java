package com.behoh.challenge.domain.participation.exception;

import com.behoh.challenge.domain.exception.NotFoundException;

public class ParticipationNotFoundException extends NotFoundException {
    public ParticipationNotFoundException(Long userId, Long eventId) {
        super("No Participation with composite ID userId=["+userId+"] eventId=["+eventId+"] was found!");
    }
}
