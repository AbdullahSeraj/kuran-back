package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.*;
import com.kuran.dashboard.io.LessonReq;
import com.kuran.dashboard.io.LessonRes;
import com.kuran.dashboard.io.SessionReq;
import com.kuran.dashboard.io.SessionRes;
import com.kuran.dashboard.repository.InstituteRepo;
import com.kuran.dashboard.repository.LessonRepo;
import com.kuran.dashboard.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    private final InstituteRepo instituteRepo;

    @Override
    public List<LessonRes> all() {
        return lessonRepo.findAllByArchivedFalse().stream().map(l -> convertToRes(l)).collect(Collectors.toList());
    }

    @Override
    public LessonRes add(LessonReq req) {
        LessonEntity newLesson = convertToEntity(req);
        newLesson = lessonRepo.save(newLesson);
        return convertToRes(newLesson);
    }

    @Override
    public void archive(String lessonId) {
        LessonEntity lesson = lessonRepo.findByLessonId(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found: " + lessonId));

        lesson.setArchived(true);
        lessonRepo.save(lesson);
    }

    private LessonEntity convertToEntity(LessonReq req) {
        InstituteEntity instituteEntity = instituteRepo.findByInstituteId(req.getInstituteId()).get();

        return LessonEntity.builder()
                .lessonId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .content(req.getContent())
                .deadline(req.getDeadline())
                .institute(instituteEntity)
                .build();
    }

    private LessonRes convertToRes(LessonEntity entity) {
        return LessonRes.builder()
                .lessonId(entity.getLessonId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .deadline(entity.getDeadline())
                .instituteId(entity.getInstitute() != null ? entity.getInstitute().getInstituteId() : null)
                .instituteName(entity.getInstitute() != null ? entity.getInstitute().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
