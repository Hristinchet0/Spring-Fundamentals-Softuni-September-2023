package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.enums.CategoryEnum;
import com.example.coffeeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if (categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setName(categoryNameEnum);
                    switch (categoryNameEnum) {
                        case CAKE -> category.setNeededTime(10);
                        case DRINK -> category.setNeededTime(1);
                        case COFFEE -> category.setNeededTime(2);
                        case OTHER -> category.setNeededTime(5);
                    }
                    categoryRepository.save(category);
                });
    }

    public Category findByCategoryNameEnum(CategoryEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
