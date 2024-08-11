package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class CheckInNotAllowedException extends ChallengeException {
    public CheckInNotAllowedException() {
        super("User cannot check in to event. Entries are allowed one hour before the event start and until the event end!");
    }
}
