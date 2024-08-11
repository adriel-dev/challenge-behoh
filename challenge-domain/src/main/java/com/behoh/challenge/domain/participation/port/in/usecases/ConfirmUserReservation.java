package com.behoh.challenge.domain.participation.port.in.usecases;

public interface ConfirmUserReservation {
    void confirm(Long userId, Long eventId);
}
