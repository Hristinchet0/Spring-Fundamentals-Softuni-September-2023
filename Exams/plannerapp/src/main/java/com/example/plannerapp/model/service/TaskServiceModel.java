package com.example.plannerapp.model.service;

import com.example.plannerapp.model.entity.Priority;
import com.example.plannerapp.model.entity.User;
import com.example.plannerapp.model.enums.PriorityEnum;

import java.time.LocalDate;

public class TaskServiceModel {

    private Long id;

    private String description;

    private LocalDate dueDate;

    private PriorityEnum priority;

    private User user;

    public TaskServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
