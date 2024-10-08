package com.behoh.challenge.persistence.participation.repository;

import com.behoh.challenge.domain.event.exception.RegistrationNotAllowedException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.participation.exception.ParticipationNotFoundException;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.out.DeleteParticipationPort;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import com.behoh.challenge.persistence.event.converter.EventPersistenceConverter;
import com.behoh.challenge.persistence.participation.converter.ParticipationPersistenceConverter;
import com.behoh.challenge.persistence.participation.entity.ParticipationId;
import com.behoh.challenge.persistence.user.converter.UserPersistenceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ParticipationRepository implements FindParticipationPort, SaveParticipationPort, DeleteParticipationPort {

    private final ParticipationJpaRepository participationJpaRepository;
    private final FindEventPort findEventPort;
    private final FindUserPort findUserPort;
    private final ParticipationPersistenceConverter participationConverter;
    private final UserPersistenceConverter userConverter;
    private final EventPersistenceConverter eventConverter;

    @Override
    public Participation findParticipation(Long userId, Long eventId) {
        var foundParticipation = participationJpaRepository.findById(new ParticipationId(eventId, userId))
                .orElseThrow(() -> new ParticipationNotFoundException(userId, eventId));
        return participationConverter.participationEntityToParticipation(foundParticipation);
    }

    @Override
    public int countUsersConfirmed(Long eventId) {
        return participationJpaRepository.countByIdEventIdAndConfirmedTrue(eventId);
    }

    @Override
    public List<User> findAllUsersWithConfirmedRegistration(Long eventId) {
        findEventPort.findEventById(eventId);
        return participationJpaRepository.findByIdEventIdAndConfirmedTrue(eventId).stream()
                .map(
                        (participation) ->  userConverter.userEntityToUser(participation.getUser())
                ).toList();
    }

    @Override
    public List<User> findAllUsersWithReservation(Long eventId) {
        findEventPort.findEventById(eventId);
        return participationJpaRepository.findByIdEventIdAndReservationDateTimeNotNull(eventId).stream()
                .map(
                        (participation -> userConverter.userEntityToUser(participation.getUser()))
                ).toList();
    }

    @Override
    public List<User> findAllUsersWithRegistrationOrReservation(Long eventId) {
        findEventPort.findEventById(eventId);
        return participationJpaRepository.findByIdEventIdAndConfirmedTrueOrReservationDateTimeNotNull(eventId).stream()
                .map(
                        (participation) -> userConverter.userEntityToUser(participation.getUser())
                ).toList();
    }

    @Override
    public List<Event> findAllUsersEventsRegistrations(Long userId) {
        findUserPort.findUserById(userId);
        return participationJpaRepository.findByIdUserId(userId).stream()
                .map(
                        (participation -> eventConverter.eventEntityToEvent(participation.getEvent()))
                ).toList();
    }

    @Override
    public Participation saveParticipation(Participation participation) {
        var participationId = new ParticipationId(participation.getEvent().getId(), participation.getUser().getId());
        if(participationJpaRepository.existsById(participationId)) {
            throw new RegistrationNotAllowedException(participation.getUser().getId());
        }
        var participationToSave = participationConverter.participationToParticipationEntity(participation);
        return participationConverter.participationEntityToParticipation(participationJpaRepository.save(participationToSave));
    }

    @Override
    public Participation updateParticipation(Participation participation) {
        var eventId = participation.getEvent().getId();
        var userId = participation.getUser().getId();
        var participationId = new ParticipationId(eventId, userId);
        var foundParticipation = participationJpaRepository.findById(participationId)
                .orElseThrow(() -> new ParticipationNotFoundException(userId, eventId));
        foundParticipation.setReservationDateTime(participation.getReservationDateTime());
        foundParticipation.setCheckInDateTime(participation.getCheckInDateTime());
        foundParticipation.setConfirmed(participation.isConfirmed());
        return participationConverter.participationEntityToParticipation(
                participationJpaRepository.save(foundParticipation)
        );
    }

    @Override
    public void deleteParticipation(Long userId, Long eventId) {
        var participationId = new ParticipationId(eventId, userId);
        participationJpaRepository.deleteById(participationId);
    }
}
