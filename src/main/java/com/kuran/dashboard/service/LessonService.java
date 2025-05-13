package com.kuran.dashboard.service;

import com.kuran.dashboard.io.LessonReq;
import com.kuran.dashboard.io.LessonRes;

import java.util.List;

public interface LessonService {
    List<LessonRes> all();

    LessonRes add(LessonReq req);

    void archive(String lessonId);
}
