package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.SessionReq;
import com.kuran.dashboard.io.SessionRes;
import com.kuran.dashboard.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public List<SessionRes> all() {
        return sessionService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionRes add(@RequestBody SessionReq req) {
        return sessionService.add(req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String sessionId) {
        try {
            sessionService.delete(sessionId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found: " + sessionId);
        }
    }
}
