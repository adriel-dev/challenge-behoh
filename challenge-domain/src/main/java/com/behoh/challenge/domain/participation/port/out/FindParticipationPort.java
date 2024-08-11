package com.behoh.challenge.domain.participation.port.out;

import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.user.model.User;

import java.util.List;

public interface FindParticipationPort {
    Participation findParticipation(Long userId, Long eventId);
    int countUsersConfirmed(Long eventId);
    List<User> findAllUsersWithConfirmedRegistration(Long eventId);
    List<User> findAllUsersWithReservation(Long eventId);
    List<User> findAllUsersWithRegistrationOrReservation(Long eventId);
    List<Event> findAllUsersEventsRegistrations(Long userId);
}
