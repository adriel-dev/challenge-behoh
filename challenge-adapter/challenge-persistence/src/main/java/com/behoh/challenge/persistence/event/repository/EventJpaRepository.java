package com.behoh.challenge.persistence.event.repository;

import com.behoh.challenge.persistence.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {
}
