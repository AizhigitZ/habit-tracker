package com.example.habittracker.service;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.Progress;
import com.example.habittracker.repository.HabitRepository;
import com.example.habittracker.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private HabitRepository habitRepository;

    public List<Progress> getProgressByHabit(Long habitId) {
        return progressRepository.findByHabitId(habitId);
    }

    public Progress logProgress(Long habitId, Progress progress) {
        Habit habit = habitRepository.findById(habitId).orElseThrow();
        progress.setHabit(habit);

        Progress existing = progressRepository.findByHabitIdAndDate(habitId, progress.getDate());
        if (existing != null) {
            existing.setCompleted(progress.isCompleted());
            existing.setNotes(progress.getNotes());
            return progressRepository.save(existing);
        }
        return progressRepository.save(progress);
    }

    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }
}