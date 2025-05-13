package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.InstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituteRepo extends JpaRepository<InstituteEntity, Long> {
    Optional<InstituteEntity> findByInstituteId(String instituteId);
}
