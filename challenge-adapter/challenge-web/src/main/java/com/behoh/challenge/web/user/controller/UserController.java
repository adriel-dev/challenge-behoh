package com.behoh.challenge.web.user.controller;

import com.behoh.challenge.domain.user.port.in.usecases.CreateUser;
import com.behoh.challenge.domain.user.port.in.usecases.GetUserEventsRegistrations;
import com.behoh.challenge.web.event.converter.EventWebConverter;
import com.behoh.challenge.web.user.converter.UserWebConverter;
import com.behoh.challenge.web.user.dto.request.CreateUserRequest;
import com.behoh.challenge.web.user.dto.response.CreateUserResponse;
import com.behoh.challenge.web.user.dto.response.GetUserRegistrationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final CreateUser createUser;
    private final GetUserEventsRegistrations getUserEventsRegistrations;
    private final UserWebConverter userConverter;
    private final EventWebConverter eventConverter;

    @PostMapping
    public ResponseEntity<CreateUserResponse> saveUser(@RequestBody CreateUserRequest createUserRequest) {
        var createdUser = createUser.create(userConverter.createUserRequestToUser(createUserRequest));
        return status(CREATED).body(userConverter.userToCreateUserResponse(createdUser));
    }

    @GetMapping("/events")
    public ResponseEntity<GetUserRegistrationsResponse> getUserEventsRegistrations(@RequestParam Long userId) {
        var eventDtoList = getUserEventsRegistrations.get(userId).stream().map(eventConverter::eventToEventDto).toList();
        return ok().body(new GetUserRegistrationsResponse(userId, eventDtoList));
    }

}
