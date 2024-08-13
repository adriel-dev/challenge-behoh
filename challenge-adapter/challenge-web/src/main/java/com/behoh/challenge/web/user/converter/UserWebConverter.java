package com.behoh.challenge.web.user.converter;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.web.event.dto.UserDTO;
import com.behoh.challenge.web.user.dto.request.CreateUserRequest;
import com.behoh.challenge.web.user.dto.response.CreateUserResponse;

public interface UserWebConverter {
    User createUserRequestToUser(CreateUserRequest createUserRequest);
    CreateUserResponse userToCreateUserResponse(User user);
    UserDTO userToUserDto(User user);
}
