package com.behoh.challenge.domain.participation.port.in.usecases;

public interface CheckInUserToEvent {
    void checkIn(Long userId, Long eventId);
}