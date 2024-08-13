package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.in.usecases.CreateUser;
import com.behoh.challenge.domain.user.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserImpl implements CreateUser {

    private final SaveUserPort saveUserPort;

    @Override
    public User create(User user) {
        return saveUserPort.saveUser(user);
    }

}
