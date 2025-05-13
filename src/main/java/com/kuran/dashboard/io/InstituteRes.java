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
public class InstituteRes {
    private String instituteId;
    private String name;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private List<String> centerNames;
    private List<String> lessonTitles;
    private List<String> exerciseTitles;
}
