package com.behoh.challenge.persistence.participation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class ParticipationId implements Serializable {
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "user_id")
    private Long userId;
}
