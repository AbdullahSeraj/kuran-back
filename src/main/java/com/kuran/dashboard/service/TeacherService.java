package com.kuran.dashboard.service;

import com.kuran.dashboard.io.TeacherReq;
import com.kuran.dashboard.io.TeacherRes;

import java.util.List;

public interface TeacherService {
    List<TeacherRes> all();

    TeacherRes add(TeacherReq req);
}
