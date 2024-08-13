package com.behoh.challenge.web.event.config;

import com.behoh.challenge.domain.event.port.in.usecases.CreateEvent;
import com.behoh.challenge.domain.event.port.in.usecases.impl.CreateEventImpl;
import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.event.port.out.SaveEventPort;
import com.behoh.challenge.domain.participation.port.in.usecases.*;
import com.behoh.challenge.domain.participation.port.in.usecases.impl.*;
import com.behoh.challenge.domain.participation.port.out.DeleteParticipationPort;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBeansConfiguration {

    @Bean
    public CreateEvent createEvent(SaveEventPort saveEventPort) {
        return new CreateEventImpl(saveEventPort);
    }

    @Bean
    CreateUserReservationForEvent createReservation(
            SaveParticipationPort saveParticipationPort,
            FindParticipationPort findParticipationPort,
            FindUserPort findUserPort,
            FindEventPort findEventPort
    ) {
        return new CreateUserReservationForEventImpl(
                saveParticipationPort, findParticipationPort, findEventPort, findUserPort
        );
    }

    @Bean
    ConfirmUserReservation confirmReservation(
            SaveParticipationPort saveParticipationPort,
            FindParticipationPort findParticipationPort
    ) {
        return new ConfirmUserReservationImpl(saveParticipationPort, findParticipationPort);
    }

    @Bean
    GetEventRegisteredUsers getEventRegisteredUsers(FindParticipationPort findParticipationPort) {
        return new GetEventRegisteredUsersImpl(findParticipationPort);
    }

    @Bean
    RegisterUserToEvent registerUserToEvent(
            SaveParticipationPort saveParticipationPort,
            FindParticipationPort findParticipationPort,
            FindEventPort findEventPort,
            FindUserPort findUserPort
    ) {
        return new RegisterUserToEventImpl(saveParticipationPort, findParticipationPort, findEventPort, findUserPort);
    }

    @Bean
    UnregisterUserFromEvent unregisterUserFromEvent(
            FindParticipationPort findParticipationPort,
            DeleteParticipationPort deleteParticipationPort
    ) {
        return new UnregisterUserFromEventImpl(findParticipationPort, deleteParticipationPort);
    }

    @Bean
    CheckInUserToEvent checkInUserToEvent(
            FindParticipationPort findParticipationPort,
            SaveParticipationPort saveParticipationPort
    ) {
        return new CheckInUserToEventImpl(findParticipationPort, saveParticipationPort);
    }

}
