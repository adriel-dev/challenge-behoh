package com.behoh.challenge.persistence.participation.entity;

import com.behoh.challenge.persistence.event.entity.EventEntity;
import com.behoh.challenge.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_event_user")
public class ParticipationEntity {
    @EmbeddedId
    ParticipationId id;
    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private EventEntity event;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
    private LocalDateTime checkInDateTime = null;
    private LocalDateTime reservationDateTime = null;
    private boolean confirmed = false;
}
