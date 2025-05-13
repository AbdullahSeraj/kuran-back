package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.LessonReq;
import com.kuran.dashboard.io.LessonRes;
import com.kuran.dashboard.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonRes> all() {
        return lessonService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonRes add(@RequestBody LessonReq req) {
        return lessonService.add(req);
    }

    @DeleteMapping("/{id}")
    public void archive(@PathVariable("id") String lessonId) {
        try {
            lessonService.archive(lessonId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found: " + lessonId);
        }
    }
}
