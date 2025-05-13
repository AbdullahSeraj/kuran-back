package com.kuran.dashboard.service;

import com.kuran.dashboard.io.StudentReq;
import com.kuran.dashboard.io.StudentRes;

import java.util.List;

public interface StudentService {
    List<StudentRes> all();

    StudentRes add(StudentReq req);
}
