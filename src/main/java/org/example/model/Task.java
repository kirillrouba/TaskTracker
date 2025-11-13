package org.example.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate createdAt;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — %s (создано: %s, выполнено: %s)",
                id, title, description, createdAt, completed ? "Да" : "Нет");
    }
}
