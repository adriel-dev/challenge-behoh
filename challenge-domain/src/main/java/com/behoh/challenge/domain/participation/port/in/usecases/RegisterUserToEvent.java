package com.behoh.challenge.domain.participation.port.in.usecases;

public interface RegisterUserToEvent {
    void register(Long userId, Long eventId);
}
