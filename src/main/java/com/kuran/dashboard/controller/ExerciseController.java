package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.ExerciseReq;
import com.kuran.dashboard.io.ExerciseRes;
import com.kuran.dashboard.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseRes> all() {
        return exerciseService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseRes add(@RequestBody ExerciseReq req) {
        return exerciseService.add(req);
    }

    @DeleteMapping("/{id}")
    public void archive(@PathVariable("id") String exerciseId) {
        try {
            exerciseService.archive(exerciseId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found: " + exerciseId);
        }
    }
}