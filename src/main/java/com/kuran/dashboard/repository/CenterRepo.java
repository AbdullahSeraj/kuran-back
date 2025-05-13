package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.CenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterRepo extends JpaRepository<CenterEntity, Long> {
    Optional<CenterEntity> findByCenterId(String centerId);
}
