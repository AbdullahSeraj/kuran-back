package com.kuran.dashboard.repository;

import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByStudentId(String studentId);
}
