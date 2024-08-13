package com.behoh.challenge.persistence.participation.converter;

import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.persistence.event.converter.EventPersistenceConverter;
import com.behoh.challenge.persistence.participation.entity.ParticipationEntity;
import com.behoh.challenge.persistence.participation.entity.ParticipationId;
import com.behoh.challenge.persistence.user.converter.UserPersistenceConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ParticipationPersistenceConverterImpl implements ParticipationPersistenceConverter {

    private final EventPersistenceConverter eventConverter;
    private final UserPersistenceConverter userConverter;

    @Override
    public Participation participationEntityToParticipation(@NonNull ParticipationEntity participationEntity) {
        return new Participation(
                eventConverter.eventEntityToEvent(participationEntity.getEvent()),
                userConverter.userEntityToUser(participationEntity.getUser()),
                participationEntity.getCheckInDateTime(),
                participationEntity.getReservationDateTime(),
                participationEntity.isConfirmed()
        );
    }

    @Override
    public ParticipationEntity participationToParticipationEntity(@NonNull Participation participation) {
        return new ParticipationEntity(
                new ParticipationId(participation.getEvent().getId(), participation.getUser().getId()),
                eventConverter.eventToEventEntity(participation.getEvent()),
                userConverter.userToUserEntity(participation.getUser()),
                participation.getCheckInDateTime(),
                participation.getReservationDateTime(),
                participation.isConfirmed()
        );
    }

}
