package com.behoh.challenge.domain.user.port.in.usecases;

import com.behoh.challenge.domain.event.model.Event;

import java.util.List;

public interface GetUserEventsRegistrations {
    List<Event> get(Long userId);
}
