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
public class ExerciseRes {
    private String exerciseId;
    private String title;
    private String description;

    private String instituteId;
    private String instituteName;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
