package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.CenterEntity;
import com.kuran.dashboard.entity.ExerciseEntity;
import com.kuran.dashboard.entity.InstituteEntity;
import com.kuran.dashboard.entity.LessonEntity;
import com.kuran.dashboard.io.InstituteReq;
import com.kuran.dashboard.io.InstituteRes;
import com.kuran.dashboard.repository.InstituteRepo;
import com.kuran.dashboard.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {
    private final InstituteRepo instituteRepo;

    public List<InstituteRes> all() {
        return instituteRepo.findAll().stream().map(i -> convertToRes(i)).collect(Collectors.toList());
    }

    public InstituteRes add(InstituteReq req) {
        InstituteEntity newIns = convertToEntity(req);
        newIns = instituteRepo.save(newIns);
        return convertToRes(newIns);
    }

    public void delete(String instituteId) {
        InstituteEntity existingIns = instituteRepo.findByInstituteId(instituteId)
                .orElseThrow(() -> new RuntimeException("Institute not found: " + instituteId));

        if ((existingIns.getCenters() != null && !existingIns.getCenters().isEmpty()) ||
                (existingIns.getLessons() != null && !existingIns.getLessons().isEmpty()) ||
                (existingIns.getExercises() != null && !existingIns.getExercises().isEmpty())) {
            throw new IllegalStateException("Cannot delete institute: it has related centers, lessons, or exercises.");
        }

        instituteRepo.delete(existingIns);
    }

    private InstituteEntity convertToEntity(InstituteReq req) {
        return InstituteEntity.builder()
                .instituteId(UUID.randomUUID().toString())
                .name(req.getName())
                .address(req.getAddress())
                .build();
    }

    private InstituteRes convertToRes(InstituteEntity entity) {
        return InstituteRes.builder()
                .instituteId(entity.getInstituteId())
                .name(entity.getName())
                .address(entity.getAddress())
                .centerNames(entity.getCenters() != null
                        ? entity.getCenters().stream().map(CenterEntity::getName).collect(Collectors.toList())
                        : Collections.emptyList())
                .lessonTitles(entity.getLessons() != null
                        ? entity.getLessons().stream().map(LessonEntity::getTitle).collect(Collectors.toList())
                        : Collections.emptyList())
                .exerciseTitles(entity.getExercises() != null
                        ? entity.getExercises().stream().map(ExerciseEntity::getTitle).collect(Collectors.toList())
                        : Collections.emptyList())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
