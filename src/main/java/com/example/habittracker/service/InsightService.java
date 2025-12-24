package com.example.habittracker.service;

import com.example.habittracker.dto.InsightDto;
import com.example.habittracker.model.Progress;
import com.example.habittracker.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsightService {

    @Autowired
    private HabitService habitService;

    @Autowired
    private ProgressRepository progressRepository;

    public List<InsightDto> generateInsights(Long userId) {
        List<InsightDto> insights = new ArrayList<>();

        habitService.getHabitsByUser(userId).forEach(habit -> {
            InsightDto dto = new InsightDto();
            dto.setHabitId(habit.getId());
            dto.setHabitName(habit.getName());
            dto.setCategory(habit.getCategory());

            List<Progress> progresses = progressRepository.findByHabitId(habit.getId());

            dto.setCurrentStreak(calculateStreak(progresses));
            dto.setCompletionRate(calculateCompletionRate(progresses));

            insights.add(dto);
        });

        return insights;
    }

    private int calculateStreak(List<Progress> progresses) {
        if (progresses.isEmpty()) return 0;

        progresses.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));

        int streak = 0;
        LocalDate expected = LocalDate.now();

        for (Progress p : progresses) {
            if (p.getDate().isAfter(expected)) {
                continue; // skip future dates
            }
            if (p.getDate().equals(expected) && p.isCompleted()) {
                streak++;
                expected = expected.minusDays(1);
            } else {
                break;
            }
        }
        return streak;
    }

    private double calculateCompletionRate(List<Progress> progresses) {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        long totalDays = 30;

        long completed = progresses.stream()
                .filter(p -> !p.getDate().isBefore(thirtyDaysAgo) && p.isCompleted())
                .count();

        return totalDays == 0 ? 0 : (completed * 100.0) / totalDays;
    }
}