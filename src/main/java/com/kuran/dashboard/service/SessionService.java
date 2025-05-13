package com.kuran.dashboard.service;

import com.kuran.dashboard.io.SessionReq;
import com.kuran.dashboard.io.SessionRes;

import java.util.List;

public interface SessionService {
    List<SessionRes> all();

    SessionRes add(SessionReq req);

    void delete(String sessionId);
}
