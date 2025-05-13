package com.kuran.dashboard.service;

import com.kuran.dashboard.io.ExerciseReq;
import com.kuran.dashboard.io.ExerciseRes;

import java.util.List;

public interface ExerciseService {
    List<ExerciseRes> all();

    ExerciseRes add(ExerciseReq req);

    void archive(String exerciseId);
}
