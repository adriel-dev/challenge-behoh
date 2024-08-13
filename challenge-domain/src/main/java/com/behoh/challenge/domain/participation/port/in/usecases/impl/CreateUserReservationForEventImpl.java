package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.in.usecases.CreateUserReservationForEvent;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canRegisterOrReserve;

@RequiredArgsConstructor
public class CreateUserReservationForEventImpl implements CreateUserReservationForEvent {

    private final SaveParticipationPort saveParticipationPort;
    private final FindParticipationPort findParticipationPort;
    private final FindEventPort findEventPort;
    private final FindUserPort findUserPort;

    @Override
    public void reserve(Long userId, Long eventId) {
        var foundEvent = findEventPort.findEventById(eventId);
        var currentlyConfirmedUsers = findParticipationPort.countUsersConfirmed(eventId);
        if(canRegisterOrReserve(foundEvent, currentlyConfirmedUsers)) {
            var foundUser = findUserPort.findUserById(userId);
            var participation = new Participation(foundEvent, foundUser, LocalDateTime.now());
            saveParticipationPort.saveParticipation(participation);
        }
    }

}
