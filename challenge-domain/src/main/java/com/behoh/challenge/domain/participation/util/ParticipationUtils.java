package com.behoh.challenge.domain.participation.util;

import com.behoh.challenge.domain.event.exception.*;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.model.Participation;

import java.time.LocalDateTime;

public class ParticipationUtils {

    public static boolean canRegisterOrReserve(Event event, int currentlyConfirmedUsers) {
        return hasAvailableSpots(event, currentlyConfirmedUsers) && eventHasNotStarted(event);
    }

    public static boolean canUnregisterFromEvent(Participation participation) {
        if (participation.getCheckInDateTime() != null) {
            throw new UnregisterNotAllowedException();
        }
        return true;
    }

    public static boolean hasAvailableSpots(Event event, int currentlyConfirmedUsers) {
        if (currentlyConfirmedUsers >= event.getCapacity()) {
            throw new NoSpotsAvailableException();
        }
        return true;
    }

    public static boolean eventHasNotStarted(Event event) {
        if (LocalDateTime.now().isAfter(event.getStartDateTime())) {
            throw new EventAlreadyStartedException();
        }
        return true;
    }

    public static boolean canCheckIn(Participation participation) {
        if (participation.getCheckInDateTime() != null) {
            throw new CheckInAlreadyPerformedException();
        }
        var event = participation.getEvent();
        var currentDateTime = LocalDateTime.now();
        var entryAllowedDateTime = event.getStartDateTime().minusHours(1L);
        if (currentDateTime.isBefore(entryAllowedDateTime) || currentDateTime.isAfter(event.getEndDateTime())) {
            throw new CheckInNotAllowedException();
        }
        return true;
    }

}
