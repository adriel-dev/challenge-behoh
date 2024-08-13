package com.behoh.challenge.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Event {
    private Long id;
    private String name;
    private int capacity;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
