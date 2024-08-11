package com.behoh.domain.user.port.in.usecases;

import com.behoh.domain.user.model.User;

public interface CreateUser {
    User create(User user);
}
