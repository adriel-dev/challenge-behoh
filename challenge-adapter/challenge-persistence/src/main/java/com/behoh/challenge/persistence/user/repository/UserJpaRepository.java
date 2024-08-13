package com.behoh.challenge.persistence.user.repository;

import com.behoh.challenge.persistence.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
