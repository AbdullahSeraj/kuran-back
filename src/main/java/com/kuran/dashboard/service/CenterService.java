package com.kuran.dashboard.service;

import com.kuran.dashboard.io.CenterReq;
import com.kuran.dashboard.io.CenterRes;

import java.util.List;

public interface CenterService {
    List<CenterRes> all();

    CenterRes save(CenterReq req);

    void delete(String centerId);
}
