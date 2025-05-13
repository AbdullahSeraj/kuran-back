package com.kuran.dashboard.controller;

import com.kuran.dashboard.io.CenterReq;
import com.kuran.dashboard.io.CenterRes;
import com.kuran.dashboard.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/center")
public class CenterController {
    private final CenterService centerService;

    @GetMapping
    public List<CenterRes> all() {
        return centerService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CenterRes save(@RequestBody CenterReq req) {
        return centerService.save(req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String centerId) {
        try {
            centerService.delete(centerId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Center not found: " + centerId);
        }
    }
}
