package com.behoh.domain.event.usecases;

import com.behoh.domain.user.model.User;

import java.util.List;

public interface GetEventRegisteredUsers {
    List<User> get(Long eventId);
}
