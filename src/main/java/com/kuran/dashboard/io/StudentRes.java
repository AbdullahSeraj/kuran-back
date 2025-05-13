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
public class StudentRes {
    private String studentId;
    private String name;

    private String sessionId;
    private String sessionName;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
