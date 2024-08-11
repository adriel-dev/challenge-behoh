package com.behoh.domain.event.usecases;

public interface UnregisterUserFromEvent {
    void unregister(Long userID, Long eventId);
}
