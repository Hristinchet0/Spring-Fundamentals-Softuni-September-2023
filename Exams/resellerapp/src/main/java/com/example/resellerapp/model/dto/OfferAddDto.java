package com.example.resellerapp.model.dto;

import com.example.resellerapp.model.enums.ConditionEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OfferAddDto {

    @Size(min = 2, max = 50)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private Double price;

    @NotNull
    private ConditionEnum condition;

    public OfferAddDto() {
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
}
