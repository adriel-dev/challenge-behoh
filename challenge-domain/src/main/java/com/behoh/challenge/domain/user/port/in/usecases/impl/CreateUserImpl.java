package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.in.usecases.CreateUser;
import com.behoh.challenge.domain.user.port.out.SaveUserPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserImpl implements CreateUser {

    private SaveUserPort saveUserPort;

    @Override
    public User create(User user) {
        return saveUserPort.saveUser(user);
    }

}
