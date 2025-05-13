package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.ExerciseEntity;
import com.kuran.dashboard.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepo extends JpaRepository<ExerciseEntity, Long> {
    Optional<ExerciseEntity> findByExerciseId(String exerciseId);
    List<ExerciseEntity> findAllByArchivedFalse();
}
