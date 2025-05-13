package com.kuran.dashboard.io;

import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.entity.InstituteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterRes {
    private String centerId;
    private String name;

    private String instituteId;
    private String instituteName;

    private String sessionName;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
