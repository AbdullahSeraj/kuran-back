package com.kuran.dashboard.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRes {
    private String teacherId;
    private String name;
    private String email;
    private String phone;
    private String sessionId;
    private String sessionName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
