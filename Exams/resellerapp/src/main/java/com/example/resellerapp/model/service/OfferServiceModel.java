package com.example.resellerapp.model.service;

import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.entity.User;
import com.example.resellerapp.model.enums.ConditionEnum;

public class OfferServiceModel {

    private Long id;

    private String description;

    private Double price;

    private ConditionEnum condition;

    private User creator;

    private User buyer;

    public OfferServiceModel() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
