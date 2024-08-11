package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.port.in.usecases.GetUserEventsRegistrations;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetUserEventsRegistrationsImpl implements GetUserEventsRegistrations {

    private FindParticipationPort findParticipationPort;

    @Override
    public List<Event> get(Long userId) {
        return findParticipationPort.findAllUsersEventsRegistrations(userId);
    }

}
