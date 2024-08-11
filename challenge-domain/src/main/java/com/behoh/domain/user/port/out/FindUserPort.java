package com.behoh.domain.user.port.out;

import com.behoh.domain.user.model.User;

public interface FindUserPort {
    User findUserById(Long userId);
}
