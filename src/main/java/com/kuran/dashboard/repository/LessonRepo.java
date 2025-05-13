package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepo extends JpaRepository<LessonEntity, Long> {
    Optional<LessonEntity> findByLessonId(String lessonId);
    List<LessonEntity> findAllByArchivedFalse();
}
