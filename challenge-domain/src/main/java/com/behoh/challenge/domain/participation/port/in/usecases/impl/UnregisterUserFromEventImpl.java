package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.UnregisterUserFromEvent;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import lombok.AllArgsConstructor;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canUnregisterFromEvent;

@AllArgsConstructor
public class UnregisterUserFromEventImpl implements UnregisterUserFromEvent {

    private FindParticipationPort findParticipationPort;
    private SaveParticipationPort saveParticipationPort;

    @Override
    public void unregister(Long userID, Long eventId) {
        var foundParticipation = findParticipationPort.findParticipation(userID, eventId);
        if (canUnregisterFromEvent(foundParticipation)) {
            foundParticipation.setCanceled(true);
            saveParticipationPort.saveParticipation(foundParticipation);
        }
    }

}
