package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.InstituteReq;
import com.kuran.dashboard.io.InstituteRes;
import com.kuran.dashboard.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/institute")
@RequiredArgsConstructor
public class InstituteController {
    private final InstituteService instituteService;

    @GetMapping
    public List<InstituteRes> all() {
        return instituteService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstituteRes add(@RequestBody InstituteReq req) {
        return instituteService.add(req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String instituteId) {
        try {
            instituteService.delete(instituteId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Institute not found: " + instituteId);
        }
    }
}
