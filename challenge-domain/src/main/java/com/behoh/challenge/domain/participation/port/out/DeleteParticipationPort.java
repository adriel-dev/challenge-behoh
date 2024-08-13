package com.behoh.challenge.domain.participation.port.out;

public interface DeleteParticipationPort {
    void deleteParticipation(Long userId, Long eventId);
}
