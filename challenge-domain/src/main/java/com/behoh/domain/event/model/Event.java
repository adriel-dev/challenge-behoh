package com.behoh.domain.event.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    private UUID id;
    private String name;
    private int capacity;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
