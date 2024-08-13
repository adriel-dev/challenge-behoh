package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.UnregisterUserFromEvent;
import com.behoh.challenge.domain.participation.port.out.DeleteParticipationPort;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import lombok.RequiredArgsConstructor;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canUnregisterFromEvent;

@RequiredArgsConstructor
public class UnregisterUserFromEventImpl implements UnregisterUserFromEvent {

    private final FindParticipationPort findParticipationPort;
    private final DeleteParticipationPort deleteParticipationPort;

    @Override
    public void unregister(Long userId, Long eventId) {
        var foundParticipation = findParticipationPort.findParticipation(userId, eventId);
        if (canUnregisterFromEvent(foundParticipation)) {
            deleteParticipationPort.deleteParticipation(userId, eventId);
        }
    }

}
