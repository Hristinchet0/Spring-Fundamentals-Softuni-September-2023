package com.example.plannerapp.model.entity;

import com.example.plannerapp.model.enums.PriorityEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityEnum priorityName;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<Task> assignedTasks;

    public Priority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityEnum getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityEnum priorityName) {
        this.priorityName = priorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> tasks) {
        this.assignedTasks = tasks;
    }
}

