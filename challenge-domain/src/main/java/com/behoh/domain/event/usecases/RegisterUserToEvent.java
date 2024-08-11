package com.behoh.domain.event.usecases;

public interface RegisterUserToEvent {
    void register(Long userId, Long eventId);
}
