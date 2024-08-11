package com.behoh.domain.event.port.in.usecases;

public interface UnregisterUserFromEvent {
    void unregister(Long userID, Long eventId);
}
