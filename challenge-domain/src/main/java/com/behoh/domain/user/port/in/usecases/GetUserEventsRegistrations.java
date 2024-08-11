package com.behoh.domain.user.port.in.usecases;

import com.behoh.domain.event.model.Event;

import java.util.List;

public interface GetUserEventsRegistrations {
    List<Event> get(Long userId);
}
