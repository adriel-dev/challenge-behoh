package com.behoh.challenge.web.event.dto.response;

import java.time.LocalDateTime;

public record CreateEventResponse(
        Long id,
        String name,
        int capacity,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
){}
