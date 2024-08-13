package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.port.in.usecases.GetUserEventsRegistrations;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetUserEventsRegistrationsImpl implements GetUserEventsRegistrations {

    private final FindParticipationPort findParticipationPort;

    @Override
    public List<Event> get(Long userId) {
        return findParticipationPort.findAllUsersEventsRegistrations(userId);
    }

}
