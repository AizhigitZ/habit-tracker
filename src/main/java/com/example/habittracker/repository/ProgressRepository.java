package com.example.habittracker.repository;

import com.example.habittracker.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByHabitId(Long habitId);
    Progress findByHabitIdAndDate(Long habitId, LocalDate date);
}