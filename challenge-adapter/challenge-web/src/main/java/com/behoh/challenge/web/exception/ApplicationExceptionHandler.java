package com.behoh.challenge.web.exception;

import com.behoh.challenge.domain.event.exception.*;
import com.behoh.challenge.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        var responseBody = new ErrorResponse(NOT_FOUND.value(), ErrorEnum.NOT_FOUND.toString(), e.getMessage(), Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return status(NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler({
            CheckInAlreadyPerformedException.class,
            CheckInNotAllowedException.class,
            EventAlreadyStartedException.class,
            EventAlreadyEndedException.class,
            InvalidEventDatesException.class,
            NoSpotsAvailableException.class,
            UnregisterNotAllowedException.class,
            RegistrationNotAllowedException.class
    })
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<ErrorResponse> handleMultipleEventRelatedExceptions(RuntimeException e) {
        var responseBody = new ErrorResponse(BAD_REQUEST.value(), ErrorEnum.BAD_REQUEST.toString(), e.getMessage(), Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return status(BAD_REQUEST).body(responseBody);
    }

}
