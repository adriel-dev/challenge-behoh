package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.ConfirmUserReservation;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import lombok.AllArgsConstructor;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canRegisterOrReserve;

@AllArgsConstructor
public class ConfirmUserReservationImpl implements ConfirmUserReservation {

    private SaveParticipationPort saveParticipationPort;
    private FindParticipationPort findParticipationPort;

    @Override
    public void confirm(Long userId, Long eventId) {
        var foundParticipation = findParticipationPort.findParticipation(userId, eventId);
        var currentlyConfirmedUsers = findParticipationPort.countUsersConfirmed(eventId);
        if(canRegisterOrReserve(foundParticipation.getEvent(), currentlyConfirmedUsers)) {
            foundParticipation.setConfirmed(true);
            saveParticipationPort.updateParticipation(foundParticipation);
        }
    }

}
