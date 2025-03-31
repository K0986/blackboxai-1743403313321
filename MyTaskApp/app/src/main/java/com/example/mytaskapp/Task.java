package com.example.mytaskapp;

public class Task {
    private int id;
    private String title;
    private String description;
    private String timestamp;

    public Task(int id, String title, String description, String timestamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTimestamp() { return timestamp; }
}