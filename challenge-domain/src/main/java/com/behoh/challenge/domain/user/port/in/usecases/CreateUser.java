package com.behoh.challenge.domain.user.port.in.usecases;

import com.behoh.challenge.domain.user.model.User;

public interface CreateUser {
    User create(User user);
}
