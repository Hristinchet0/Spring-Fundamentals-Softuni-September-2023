package com.example.plannerapp.model.dto;

import com.example.plannerapp.model.enums.PriorityEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class TaskAddDto {

    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    @Future(message = "Due Date must be in future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private PriorityEnum priority;

    public TaskAddDto() {
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
}
