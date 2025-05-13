package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.TeacherReq;
import com.kuran.dashboard.io.TeacherRes;
import com.kuran.dashboard.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherRes> all() {
        return teacherService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherRes add(@RequestBody TeacherReq req) {
        return teacherService.add(req);
    }
}
