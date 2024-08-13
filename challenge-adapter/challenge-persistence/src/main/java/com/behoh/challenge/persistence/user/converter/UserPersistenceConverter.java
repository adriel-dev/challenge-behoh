package com.behoh.challenge.persistence.user.converter;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.persistence.user.entity.UserEntity;

public interface UserPersistenceConverter {
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
}
