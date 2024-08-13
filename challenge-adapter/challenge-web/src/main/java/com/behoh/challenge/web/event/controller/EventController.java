package com.behoh.challenge.web.event.controller;

import com.behoh.challenge.domain.event.port.in.usecases.CreateEvent;
import com.behoh.challenge.domain.participation.port.in.usecases.*;
import com.behoh.challenge.web.event.converter.EventWebConverter;
import com.behoh.challenge.web.event.dto.request.CreateEventRequest;
import com.behoh.challenge.web.event.dto.response.CreateEventResponse;
import com.behoh.challenge.web.event.dto.response.GetRegisteredUsersResponse;
import com.behoh.challenge.web.user.converter.UserWebConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/event")
public class EventController {

    private final CreateEvent createEvent;
    private final GetEventRegisteredUsers getEventRegisteredUsers;
    private final CreateUserReservationForEvent createUserReservationForEvent;
    private final ConfirmUserReservation confirmUserReservation;
    private final RegisterUserToEvent registerUserToEvent;
    private final UnregisterUserFromEvent unregisterUserFromEvent;
    private final CheckInUserToEvent checkInUserToEvent;

    private final EventWebConverter eventConverter;
    private final UserWebConverter userConverter;

    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        var eventToCreate = eventConverter.createEventRequestToEvent(createEventRequest);
        var createdEvent = createEvent.create(eventToCreate);
        return status(CREATED).body(eventConverter.eventToCreateEventResponse(createdEvent));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<GetRegisteredUsersResponse> getEventRegisteredUsers(@PathVariable Long eventId) {
        var userDtoList = getEventRegisteredUsers.get(eventId).stream().map((userConverter::userToUserDto)).toList();
        var eventRegisteredUsers = new GetRegisteredUsersResponse(eventId, userDtoList);
        return ok().body(eventRegisteredUsers);
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> createReservation(@RequestParam Long eventId, @RequestParam Long userId) {
        createUserReservationForEvent.reserve(userId, eventId);
        return status(CREATED).build();
    }

    @PutMapping("/reservation/confirm")
    public ResponseEntity<Void> confirmReservation(@RequestParam Long eventId, @RequestParam Long userId) {
        confirmUserReservation.confirm(userId, eventId);
        return ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUserToEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        registerUserToEvent.register(userId, eventId);
        return status(CREATED).build();
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<Void> unregisterUserFromEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        unregisterUserFromEvent.unregister(userId, eventId);
        return ok().build();
    }

    @PutMapping("/checkin")
    public ResponseEntity<Void> checkInUserToEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        checkInUserToEvent.checkIn(userId, eventId);
        return ok().build();
    }

}
