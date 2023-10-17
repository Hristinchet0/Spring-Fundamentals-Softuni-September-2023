package com.example.plannerapp.model.dto;

import com.example.plannerapp.model.enums.PriorityEnum;

import java.time.LocalDate;

public class TaskViewDto {

    private Long id;

    private PriorityEnum priority;

    private LocalDate dueDate;

    private String description;

    public TaskViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
