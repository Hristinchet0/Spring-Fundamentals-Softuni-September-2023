package com.example.resellerapp.model.dto;

import com.example.resellerapp.model.enums.ConditionEnum;

public class OfferViewDto {

    private Long id;

    private String description;

    private Double price;

    private ConditionEnum conditionName;

    private String creatorUsername;

    private Long buyerId;

    public OfferViewDto() {
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

    public ConditionEnum getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionEnum conditionName) {
        this.conditionName = conditionName;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
