package com.behoh.challenge.domain.participation.port.in.usecases;

public interface UnregisterUserFromEvent {
    void unregister(Long userId, Long eventId);
}
