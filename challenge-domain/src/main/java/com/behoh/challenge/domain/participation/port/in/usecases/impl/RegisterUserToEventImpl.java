package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.in.usecases.RegisterUserToEvent;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import lombok.AllArgsConstructor;

import static com.behoh.challenge.domain.participation.util.ParticipationUtils.canRegisterOrReserve;

@AllArgsConstructor
public class RegisterUserToEventImpl implements RegisterUserToEvent {

    private SaveParticipationPort saveParticipationPort;
    private FindParticipationPort findParticipationPort;
    private FindEventPort findEventPort;
    private FindUserPort findUserPort;

    @Override
    public void register(Long userId, Long eventId) {
        var currentlyConfirmedUsers = findParticipationPort.countUsersConfirmed(eventId);
        var foundEvent = findEventPort.findEventById(eventId);

        if(canRegisterOrReserve(foundEvent, currentlyConfirmedUsers)) {
            var foundUser = findUserPort.findUserById(userId);
            var participation = new Participation(foundEvent, foundUser);
            participation.setConfirmed(true);
            saveParticipationPort.saveParticipation(participation);
        }
    }

}
