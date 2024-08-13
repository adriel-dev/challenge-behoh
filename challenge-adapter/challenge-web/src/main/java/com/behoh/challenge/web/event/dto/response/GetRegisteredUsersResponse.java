package com.behoh.challenge.web.event.dto.response;

import com.behoh.challenge.web.event.dto.UserDTO;

import java.util.List;

public record GetRegisteredUsersResponse(
        Long eventId,
        List<UserDTO> users
){}
