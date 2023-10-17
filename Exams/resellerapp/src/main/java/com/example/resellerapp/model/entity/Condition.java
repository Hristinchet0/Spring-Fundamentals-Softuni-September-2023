package com.example.resellerapp.model.entity;

import com.example.resellerapp.model.enums.ConditionEnum;

import javax.persistence.*;

@Entity
@Table(name= "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConditionEnum conditionName;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionEnum getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionEnum conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
