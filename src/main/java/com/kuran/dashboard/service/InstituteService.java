package com.kuran.dashboard.service;

import com.kuran.dashboard.io.InstituteReq;
import com.kuran.dashboard.io.InstituteRes;

import java.util.List;

public interface InstituteService {
    List<InstituteRes> all();

    InstituteRes add(InstituteReq req);

    void delete(String instituteId);
}
