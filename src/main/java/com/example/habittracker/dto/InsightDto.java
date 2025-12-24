package com.example.habittracker.dto;

import lombok.Data;

@Data
public class InsightDto {
    private Long habitId;
    private String habitName;
    private int currentStreak;
    private double completionRate;
    private String category;
}