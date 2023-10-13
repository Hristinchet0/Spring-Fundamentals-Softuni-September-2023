package com.example.shoppinglistexam.model.dto;

import com.example.shoppinglistexam.model.entity.Category;

import java.math.BigDecimal;

public class ProductViewDto {

    private Long id;

    private String name;

    private double price;

    private Category category;

    public ProductViewDto() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
