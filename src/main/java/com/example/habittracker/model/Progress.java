package com.example.habittracker.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    @JsonIgnore
    private Habit habit;

    private LocalDate date;
    private boolean completed;
    private String notes;
}