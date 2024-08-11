package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.in.usecases.GetEventRegisteredUsers;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetEventRegisteredUsersImpl implements GetEventRegisteredUsers {

    private FindParticipationPort findParticipationPort;

    @Override
    public List<User> get(Long eventId) {
        return findParticipationPort.findAllUsersWithConfirmedRegistration(eventId);
    }

}
