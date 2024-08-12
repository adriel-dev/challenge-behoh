package com.behoh.challenge.persistence.user.converter;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.persistence.user.entity.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceConverterImpl implements UserPersistenceConverter {

    @Override
    public User userEntityToUser(@NonNull UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName()
        );
    }

    @Override
    public UserEntity userToUserEntity(@NonNull User user) {
        return new UserEntity(
                user.getId(),
                user.getName()
        );
    }

}
