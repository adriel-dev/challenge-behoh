package com.behoh.challenge.web.event.dto;

import java.time.LocalDateTime;

public record EventDTO(
        Long id,
        String name,
        int capacity,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
){}
