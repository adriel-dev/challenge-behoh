package com.behoh.domain.user.port.out;

import com.behoh.domain.user.model.User;

public interface SaveUserPort {
    User saveUser(User user);
    User updateUser(User user);
}
