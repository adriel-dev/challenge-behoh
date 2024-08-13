package com.behoh.challenge.domain.participation.port.in.usecases;

import com.behoh.challenge.domain.user.model.User;

import java.util.List;

public interface GetEventRegisteredUsers {
    List<User> get(Long eventId);
}
