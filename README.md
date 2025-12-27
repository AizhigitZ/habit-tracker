# Personal Habit & Productivity Tracker – Backend API

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-brightgreen?logo=springboot)
![H2](https://img.shields.io/badge/H2-Database-lightgrey?logo=h2)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?logo=gradle)

A clean, well-structured **RESTful backend API** built with Spring Boot for tracking personal habits and measuring productivity. It supports habit creation, daily progress logging, and automatic calculation of streaks and completion rates.

Perfect as a foundation for habit-tracking mobile or web applications.

## Features

- **User management** – Create and manage users
- **Habit management** – Create, update, delete habits with categories and reminder times
- **Daily progress logging** – Mark habits as completed/incomplete with optional notes
- **Productivity insights** – Automatically calculate:
  - Current streak (consecutive completed days ending today)
  - Completion rate over the last 30 days
- Clean, nested REST endpoints
- In-memory H2 database (easy setup, no external DB required)
- Lombok for reduced boilerplate
- Proper handling of bidirectional relationships (no infinite JSON recursion)

## Tech Stack

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA** (Hibernate)
- **H2 In-Memory Database**
- **Lombok**
- **Gradle** (Groovy DSL)

## Project Structure
habit-tracker/
├── src/main/java/com/example/habittracker/
│   ├── HabitTrackerApplication.java
│   ├── controller/     → REST endpoints
│   ├── service/        → Business logic & insights
│   ├── repository/     → JPA repositories
│   ├── model/          → Entities (User, Habit, Progress)
│   └── dto/            → InsightDto
├── src/main/resources/
│   └── application.properties
├── build.gradle
└── settings.gradle
text## Quick Start

### Prerequisites
- Java 21 JDK
- Gradle (or use the wrapper)

### Run the Application

```bash
# Clone or use your local project directory

# Run with Gradle wrapper
./gradlew bootRun

# On Windows
gradlew.bat bootRun
The API will start at:
http://localhost:8080

Key Implementation Details

@Table(name = "app_users") on User entity to avoid SQL reserved keyword issues
@JsonIgnore on parent references to prevent infinite recursion in JSON responses
Smart daily progress handling: update existing entry if date already logged
Insight calculations use date sorting and Java streams for accuracy

Future Improvements

Add authentication (Spring Security + JWT)
Input validation with @Valid and custom error handling
Support for persistent database (PostgreSQL/MySQL)
Reminder scheduling integration
Frontend integration (React, Flutter, etc.)

Author
Aizhigit Zhigitekov
December 2025
