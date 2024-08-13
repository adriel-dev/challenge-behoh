package com.behoh.challenge.persistence.user.repository;

import com.behoh.challenge.domain.user.exception.UserNotFoundException;
import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import com.behoh.challenge.domain.user.port.out.SaveUserPort;
import com.behoh.challenge.persistence.user.converter.UserPersistenceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository implements SaveUserPort, FindUserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceConverter converter;

    @Override
    public User findUserById(Long userId) {
        var foundUser = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return converter.userEntityToUser(foundUser);
    }

    @Override
    public User saveUser(User user) {
        var userToSave = converter.userToUserEntity(user);
        var savedUser = userJpaRepository.save(userToSave);
        return converter.userEntityToUser(savedUser);
    }

}
