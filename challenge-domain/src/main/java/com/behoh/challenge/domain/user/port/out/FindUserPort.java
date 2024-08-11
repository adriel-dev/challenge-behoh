package com.behoh.challenge.domain.user.port.out;

import com.behoh.challenge.domain.user.model.User;

public interface FindUserPort {
    User findUserById(Long userId);
}
