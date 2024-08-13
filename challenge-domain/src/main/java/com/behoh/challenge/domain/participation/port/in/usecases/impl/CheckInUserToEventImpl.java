package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.CheckInUserToEvent;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canCheckIn;

@RequiredArgsConstructor
public class CheckInUserToEventImpl implements CheckInUserToEvent {

    private final FindParticipationPort findParticipationPort;
    private final SaveParticipationPort saveParticipationPort;

    @Override
    public void checkIn(Long userID, Long eventId) {
        var foundParticipation = findParticipationPort.findParticipation(userID, eventId);
        if(canCheckIn(foundParticipation)) {
            foundParticipation.setCheckInDateTime(LocalDateTime.now());
            saveParticipationPort.updateParticipation(foundParticipation);
        }
    }

}
