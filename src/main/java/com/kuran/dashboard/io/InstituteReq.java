package com.kuran.dashboard.io;

import com.kuran.dashboard.entity.CenterEntity;
import com.kuran.dashboard.entity.ExerciseEntity;
import com.kuran.dashboard.entity.LessonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituteReq {
    private String name;
    private String address;
}
