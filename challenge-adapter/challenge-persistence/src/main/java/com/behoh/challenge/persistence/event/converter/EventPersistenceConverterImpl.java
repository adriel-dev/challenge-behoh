package com.behoh.challenge.persistence.event.converter;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.persistence.event.entity.EventEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EventPersistenceConverterImpl implements EventPersistenceConverter {

    @Override
    public Event eventEntityToEvent(@NonNull EventEntity eventEntity) {
        return new Event(
                eventEntity.getId(),
                eventEntity.getName(),
                eventEntity.getCapacity(),
                eventEntity.getStartDateTime(),
                eventEntity.getEndDateTime()
        );
    }

    @Override
    public EventEntity eventToEventEntity(@NonNull Event event) {
        return new EventEntity(
                event.getId(),
                event.getName(),
                event.getCapacity(),
                event.getStartDateTime(),
                event.getEndDateTime()
        );
    }

}
