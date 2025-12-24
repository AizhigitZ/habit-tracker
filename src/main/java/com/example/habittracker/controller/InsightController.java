package com.example.habittracker.controller;

import com.example.habittracker.dto.InsightDto;
import com.example.habittracker.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/insights")
public class InsightController {

    @Autowired
    private InsightService insightService;

    @GetMapping
    public List<InsightDto> generateInsights(@PathVariable Long userId) {
        return insightService.generateInsights(userId);
    }
}