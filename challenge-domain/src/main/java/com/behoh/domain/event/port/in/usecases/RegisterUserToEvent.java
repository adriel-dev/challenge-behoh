package com.behoh.domain.event.port.in.usecases;

public interface RegisterUserToEvent {
    void register(Long userId, Long eventId);
}
