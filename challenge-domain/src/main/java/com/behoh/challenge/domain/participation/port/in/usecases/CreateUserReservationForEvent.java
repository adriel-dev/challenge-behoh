package com.behoh.challenge.domain.participation.port.in.usecases;

public interface CreateUserReservationForEvent {
    void reserve(Long userId, Long eventId);
}
