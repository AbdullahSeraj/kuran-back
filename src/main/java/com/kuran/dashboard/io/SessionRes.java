package com.kuran.dashboard.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionRes {
    private String sessionId;
    private String name;

    private String centerId;
    private String centerName;

    private String teacherId;
    private String teacherName;

    private List<String> studentNames;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
