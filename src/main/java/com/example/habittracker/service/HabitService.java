package com.example.habittracker.service;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.User;
import com.example.habittracker.repository.HabitRepository;
import com.example.habittracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Habit> getHabitsByUser(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    public Optional<Habit> getHabitById(Long id) {
        return habitRepository.findById(id);
    }

    public Habit createHabit(Long userId, Habit habit) {
        User user = userRepository.findById(userId).orElseThrow();
        habit.setUser(user);
        return habitRepository.save(habit);
    }

    public Habit updateHabit(Long id, Habit habitDetails) {
        Habit habit = habitRepository.findById(id).orElseThrow();
        habit.setName(habitDetails.getName());
        habit.setDescription(habitDetails.getDescription());
        habit.setCategory(habitDetails.getCategory());
        habit.setReminderTime(habitDetails.getReminderTime());
        return habitRepository.save(habit);
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
}