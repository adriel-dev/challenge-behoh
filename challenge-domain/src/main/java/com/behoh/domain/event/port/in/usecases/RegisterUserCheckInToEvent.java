package com.behoh.domain.event.port.in.usecases;

public interface RegisterUserCheckInToEvent {
    void register(Long userID, Long eventId);
}