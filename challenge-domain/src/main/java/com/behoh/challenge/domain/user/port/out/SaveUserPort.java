package com.behoh.challenge.domain.user.port.out;

import com.behoh.challenge.domain.user.model.User;

public interface SaveUserPort {
    User saveUser(User user);
    User updateUser(User user);
}
