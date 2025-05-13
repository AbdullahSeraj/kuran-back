package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.CenterEntity;
import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.entity.TeacherEntity;
import com.kuran.dashboard.io.SessionReq;
import com.kuran.dashboard.io.SessionRes;
import com.kuran.dashboard.io.TeacherReq;
import com.kuran.dashboard.io.TeacherRes;
import com.kuran.dashboard.repository.TeacherRepo;
import com.kuran.dashboard.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;

    @Override
    public List<TeacherRes> all() {
        return teacherRepo.findAll().stream().map(t -> convertToRes(t)).collect(Collectors.toList());
    }

    @Override
    public TeacherRes add(TeacherReq req) {
        TeacherEntity newTeacher = convertToEntity(req);
        newTeacher = teacherRepo.save(newTeacher);
        return convertToRes(newTeacher);
    }

    private TeacherEntity convertToEntity(TeacherReq req) {
        return TeacherEntity.builder()
                .teacherId(UUID.randomUUID().toString())
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .build();
    }

    private TeacherRes convertToRes(TeacherEntity entity) {
        return TeacherRes.builder()
                .teacherId(entity.getTeacherId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .sessionId(entity.getSession() != null ? entity.getSession().getSessionId() : null)
                .sessionName(entity.getSession() != null ? entity.getSession().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
