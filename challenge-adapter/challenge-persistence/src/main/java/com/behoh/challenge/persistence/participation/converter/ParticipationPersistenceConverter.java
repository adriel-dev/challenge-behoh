package com.behoh.challenge.persistence.participation.converter;

import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.persistence.participation.entity.ParticipationEntity;

public interface ParticipationPersistenceConverter {
    Participation participationEntityToParticipation(ParticipationEntity participationEntity);
    ParticipationEntity participationToParticipationEntity(Participation participation);
}
