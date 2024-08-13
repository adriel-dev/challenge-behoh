package com.behoh.challenge.web.event.converter;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.web.event.dto.EventDTO;
import com.behoh.challenge.web.event.dto.request.CreateEventRequest;
import com.behoh.challenge.web.event.dto.response.CreateEventResponse;

public interface EventWebConverter {
    Event createEventRequestToEvent(CreateEventRequest createEventRequest);
    CreateEventResponse eventToCreateEventResponse(Event event);
    EventDTO eventToEventDto(Event event);
}
