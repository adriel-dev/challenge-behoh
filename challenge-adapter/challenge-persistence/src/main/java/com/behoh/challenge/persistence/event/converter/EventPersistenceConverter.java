package com.behoh.challenge.persistence.event.converter;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.persistence.event.entity.EventEntity;

public interface EventPersistenceConverter {
    Event eventEntityToEvent(EventEntity eventEntity);
    EventEntity eventToEventEntity(Event event);
}
