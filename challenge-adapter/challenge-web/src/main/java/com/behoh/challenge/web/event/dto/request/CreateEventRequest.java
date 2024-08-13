package com.behoh.challenge.web.event.dto.request;

import java.time.LocalDateTime;

public record CreateEventRequest(
        String name,
        int capacity,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
){}
