package com.example.coffeeshop.model.service;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.enums.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime orderTime;

    private CategoryEnum category;

    private User employee;

    public OrderServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }


}
