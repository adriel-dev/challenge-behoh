package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.GetEventRegisteredUsers;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetEventRegisteredUsersImpl implements GetEventRegisteredUsers {

    private final FindParticipationPort findParticipationPort;

    @Override
    public List<User> get(Long eventId) {
        return findParticipationPort.findAllUsersWithConfirmedRegistration(eventId);
    }

}
