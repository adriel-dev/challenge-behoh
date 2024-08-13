package com.behoh.challenge.web.user.dto.response;

import com.behoh.challenge.web.event.dto.EventDTO;

import java.util.List;

public record GetUserRegistrationsResponse(
        Long userId,
        List<EventDTO> events
){}
