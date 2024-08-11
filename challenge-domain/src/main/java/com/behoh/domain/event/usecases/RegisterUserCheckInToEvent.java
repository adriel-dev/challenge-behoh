package com.behoh.domain.event.usecases;

public interface RegisterUserCheckInToEvent {
    void register(Long userID, Long eventId);
}