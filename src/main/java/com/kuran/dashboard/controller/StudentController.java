package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.StudentReq;
import com.kuran.dashboard.io.StudentRes;
import com.kuran.dashboard.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentRes> all() {
        return studentService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentRes add(@RequestBody StudentReq req) {
        return studentService.add(req);
    }
}