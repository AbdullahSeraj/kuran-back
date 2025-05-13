package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.entity.StudentEntity;
import com.kuran.dashboard.entity.TeacherEntity;
import com.kuran.dashboard.io.StudentReq;
import com.kuran.dashboard.io.StudentRes;
import com.kuran.dashboard.io.TeacherReq;
import com.kuran.dashboard.io.TeacherRes;
import com.kuran.dashboard.repository.SessionRepo;
import com.kuran.dashboard.repository.StudentRepo;
import com.kuran.dashboard.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final SessionRepo sessionRepo;

    @Override
    public List<StudentRes> all() {
        return studentRepo.findAll().stream().map(s -> convertToRes(s)).collect(Collectors.toList());
    }

    @Override
    public StudentRes add(StudentReq req) {
        StudentEntity newStudent = convertToEntity(req);
        newStudent = studentRepo.save(newStudent);
        return convertToRes(newStudent);
    }

    private StudentEntity convertToEntity(StudentReq req) {
        SessionEntity sessionEntity = sessionRepo.findBySessionId(req.getSessionId()).get();

        return StudentEntity.builder()
                .studentId(UUID.randomUUID().toString())
                .name(req.getName())
                .session(sessionEntity)
                .build();
    }

    private StudentRes convertToRes(StudentEntity entity) {
        return StudentRes.builder()
                .studentId(entity.getStudentId())
                .name(entity.getName())
                .sessionId(entity.getSession() != null ? entity.getSession().getSessionId() : null)
                .sessionName(entity.getSession() != null ? entity.getSession().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
