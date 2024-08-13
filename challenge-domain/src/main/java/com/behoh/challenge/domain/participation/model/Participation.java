package com.behoh.challenge.domain.participation.model;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Participation {

    private Event event;
    private User user;
    private LocalDateTime checkInDateTime = null;
    private LocalDateTime reservationDateTime = null;
    private boolean confirmed = false;

    public Participation(Event event, User user) {
        this.event = event;
        this.user = user;
    }

    public Participation(Event event, User user, LocalDateTime reservationDateTime) {
        this.event = event;
        this.user = user;
        this.reservationDateTime = reservationDateTime;
    }
}
