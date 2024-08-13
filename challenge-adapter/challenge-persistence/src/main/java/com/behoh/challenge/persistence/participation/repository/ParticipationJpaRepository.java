package com.behoh.challenge.persistence.participation.repository;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.persistence.participation.entity.ParticipationEntity;
import com.behoh.challenge.persistence.participation.entity.ParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationJpaRepository extends JpaRepository<ParticipationEntity, ParticipationId> {
    boolean existsById(ParticipationId participationId);
    int countByIdEventIdAndConfirmedTrue(Long eventId);
    List<ParticipationEntity> findByIdEventIdAndConfirmedTrue(Long eventId);
    List<ParticipationEntity> findByIdEventIdAndReservationDateTimeNotNull(Long eventId);
    List<ParticipationEntity> findByIdEventIdAndConfirmedTrueOrReservationDateTimeNotNull(Long eventId);
    List<ParticipationEntity> findByIdUserId(Long userId);
}
