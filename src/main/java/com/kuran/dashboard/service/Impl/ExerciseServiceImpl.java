package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.ExerciseEntity;
import com.kuran.dashboard.entity.InstituteEntity;
import com.kuran.dashboard.entity.LessonEntity;
import com.kuran.dashboard.io.ExerciseReq;
import com.kuran.dashboard.io.ExerciseRes;
import com.kuran.dashboard.io.LessonReq;
import com.kuran.dashboard.io.LessonRes;
import com.kuran.dashboard.repository.ExerciseRepo;
import com.kuran.dashboard.repository.InstituteRepo;
import com.kuran.dashboard.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepo exerciseRepo;
    private final InstituteRepo instituteRepo;

    @Override
    public List<ExerciseRes> all() {
        return exerciseRepo.findAllByArchivedFalse().stream().map(e -> convertToRes(e)).collect(Collectors.toList());
    }

    @Override
    public ExerciseRes add(ExerciseReq req) {
        ExerciseEntity newExer = convertToEntity(req);
        newExer = exerciseRepo.save(newExer);
        return convertToRes(newExer);
    }

    @Override
    public void archive(String exerciseId) {
        ExerciseEntity exercise = exerciseRepo.findByExerciseId(exerciseId)
                .orElseThrow(() -> new RuntimeException("Lesson not found: " + exerciseId));

        exercise.setArchived(true);
        exerciseRepo.save(exercise);
    }

    private ExerciseEntity convertToEntity(ExerciseReq req) {
        InstituteEntity instituteEntity = instituteRepo.findByInstituteId(req.getInstituteId()).get();

        return ExerciseEntity.builder()
                .exerciseId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .description(req.getDescription())
                .institute(instituteEntity)
                .build();
    }

    private ExerciseRes convertToRes(ExerciseEntity entity) {
        return ExerciseRes.builder()
                .exerciseId(entity.getExerciseId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .instituteId(entity.getInstitute() != null ? entity.getInstitute().getInstituteId() : null)
                .instituteName(entity.getInstitute() != null ? entity.getInstitute().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
