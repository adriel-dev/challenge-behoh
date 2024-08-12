package com.behoh.challenge.persistence.event.repository;

import com.behoh.challenge.domain.event.exception.EventNotFoundException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.event.port.out.SaveEventPort;
import com.behoh.challenge.persistence.event.converter.EventPersistenceConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class EventRepository implements SaveEventPort, FindEventPort {

    private EventJpaRepository eventJpaRepository;
    private EventPersistenceConverter converter;

    @Override
    public Event findEventById(Long eventId) {
        var foundEvent = eventJpaRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        return converter.eventEntityToEvent(foundEvent);
    }

    @Override
    public Event saveEvent(Event event) {
        var eventToSave = converter.eventToEventEntity(event);
        var savedEvent = eventJpaRepository.save(eventToSave);
        return converter.eventEntityToEvent(savedEvent);
    }

}
