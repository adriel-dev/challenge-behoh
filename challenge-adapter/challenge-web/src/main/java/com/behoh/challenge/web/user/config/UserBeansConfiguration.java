package com.behoh.challenge.web.user.config;

import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.port.in.usecases.CreateUser;
import com.behoh.challenge.domain.user.port.in.usecases.GetUserEventsRegistrations;
import com.behoh.challenge.domain.user.port.in.usecases.impl.CreateUserImpl;
import com.behoh.challenge.domain.user.port.in.usecases.impl.GetUserEventsRegistrationsImpl;
import com.behoh.challenge.domain.user.port.out.SaveUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeansConfiguration {

    @Bean
    public CreateUser createUser(SaveUserPort saveUserPort) {
        return new CreateUserImpl(saveUserPort);
    }

    @Bean
    public GetUserEventsRegistrations getUserEventsRegistrations(FindParticipationPort findParticipationPort){
        return new GetUserEventsRegistrationsImpl(findParticipationPort);
    }

}
