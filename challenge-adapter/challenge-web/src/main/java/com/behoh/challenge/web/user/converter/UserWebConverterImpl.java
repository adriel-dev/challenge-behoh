package com.behoh.challenge.web.user.converter;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.web.event.dto.UserDTO;
import com.behoh.challenge.web.user.dto.request.CreateUserRequest;
import com.behoh.challenge.web.user.dto.response.CreateUserResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserWebConverterImpl implements UserWebConverter {

    @Override
    public User createUserRequestToUser(@NonNull CreateUserRequest createUserRequest) {
        return new User(
                null,
                createUserRequest.name()
        );
    }

    @Override
    public CreateUserResponse userToCreateUserResponse(@NonNull User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getName()
        );
    }

    @Override
    public UserDTO userToUserDto(@NonNull User user) {
        return new UserDTO(
                user.getId(),
                user.getName()
        );
    }

}
