package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<SessionEntity, Long> {
    Optional<SessionEntity> findBySessionId(String sessionId);
}
