package com.example.habittracker.controller;

import com.example.habittracker.model.Progress;
import com.example.habittracker.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits/{habitId}/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping
    public List<Progress> getProgressByHabit(@PathVariable Long habitId) {
        return progressService.getProgressByHabit(habitId);
    }

    @PostMapping
    public Progress logProgress(@PathVariable Long habitId, @RequestBody Progress progress) {
        return progressService.logProgress(habitId, progress);
    }

    @DeleteMapping("/{progressId}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long progressId) {
        progressService.deleteProgress(progressId);
        return ResponseEntity.noContent().build();
    }
}