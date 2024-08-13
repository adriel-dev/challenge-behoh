package com.behoh.challenge.web.event.converter;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.web.event.dto.EventDTO;
import com.behoh.challenge.web.event.dto.request.CreateEventRequest;
import com.behoh.challenge.web.event.dto.response.CreateEventResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EventWebConverterImpl implements EventWebConverter {

    @Override
    public Event createEventRequestToEvent(@NonNull CreateEventRequest createEventRequest) {
        return new Event(
                null,
                createEventRequest.name(),
                createEventRequest.capacity(),
                createEventRequest.startDateTime(),
                createEventRequest.endDateTime()
        );
    }

    @Override
    public CreateEventResponse eventToCreateEventResponse(@NonNull Event event) {
        return new CreateEventResponse(
                event.getId(),
                event.getName(),
                event.getCapacity(),
                event.getStartDateTime(),
                event.getEndDateTime()
        );
    }

    @Override
    public EventDTO eventToEventDto(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getCapacity(),
                event.getStartDateTime(),
                event.getEndDateTime()
        );
    }

}
