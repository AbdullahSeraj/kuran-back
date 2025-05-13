package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByTeacherId(String teacherId);
}
