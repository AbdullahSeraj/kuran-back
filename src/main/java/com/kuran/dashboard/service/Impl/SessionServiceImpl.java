package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.CenterEntity;
import com.kuran.dashboard.entity.InstituteEntity;
import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.entity.TeacherEntity;
import com.kuran.dashboard.io.CenterReq;
import com.kuran.dashboard.io.CenterRes;
import com.kuran.dashboard.io.SessionReq;
import com.kuran.dashboard.io.SessionRes;
import com.kuran.dashboard.repository.CenterRepo;
import com.kuran.dashboard.repository.SessionRepo;
import com.kuran.dashboard.repository.TeacherRepo;
import com.kuran.dashboard.service.CenterService;
import com.kuran.dashboard.service.SessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepo sessionRepo;
    private final CenterRepo centerRepo;
    private final TeacherRepo teacherRepo;

    @Override
    public List<SessionRes> all() {
        return sessionRepo.findAll().stream().map(i -> convertToRes(i)).collect(Collectors.toList());
    }

    @Override
    public SessionRes add(SessionReq req) {
        SessionEntity newSession = convertToEntity(req);
        newSession = sessionRepo.save(newSession);
        return convertToRes(newSession);
    }

    @Override
    public void delete(String sessionId) {
        SessionEntity existingSession = sessionRepo.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found: " + sessionId));

        if (existingSession.getCenter() != null) {
            throw new IllegalStateException("Cannot delete: Session is linked to a center.");
        }

        if (existingSession.getTeacher() != null) {
            throw new IllegalStateException("Cannot delete: Session is linked to a teacher.");
        }

        sessionRepo.delete(existingSession);
    }

    private SessionEntity convertToEntity(SessionReq req) {
        CenterEntity centerEntity = centerRepo.findByCenterId(req.getCenterId()).get();
        TeacherEntity teacherEntity = teacherRepo.findByTeacherId(req.getTeacherId()).get();

        return SessionEntity.builder()
                .sessionId(UUID.randomUUID().toString())
                .name(req.getName())
                .center(centerEntity)
                .teacher(teacherEntity)
                .build();
    }

    private SessionRes convertToRes(SessionEntity entity) {
        return SessionRes.builder()
                .sessionId(entity.getSessionId())
                .name(entity.getName())
                .centerId(entity.getCenter().getCenterId())
                .centerName(entity.getCenter().getName())
                .teacherId(entity.getTeacher().getTeacherId())
                .teacherName(entity.getTeacher().getName())
                .studentNames(entity.getStudents() != null
                        ? entity.getStudents().stream().map(s -> s.getName()).collect(Collectors.toList())
                        : Collections.emptyList())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
