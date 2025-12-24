package com.example.habittracker.controller;

import com.example.habittracker.model.Habit;
import com.example.habittracker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @GetMapping
    public List<Habit> getHabitsByUser(@PathVariable Long userId) {
        return habitService.getHabitsByUser(userId);
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long habitId) {
        return habitService.getHabitById(habitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habit createHabit(@PathVariable Long userId, @RequestBody Habit habit) {
        return habitService.createHabit(userId, habit);
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long habitId, @RequestBody Habit habitDetails) {
        return ResponseEntity.ok(habitService.updateHabit(habitId, habitDetails));
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitService.deleteHabit(habitId);
        return ResponseEntity.noContent().build();
    }
}