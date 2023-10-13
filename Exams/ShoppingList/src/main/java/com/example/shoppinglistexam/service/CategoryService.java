package com.example.shoppinglistexam.service;

import com.example.shoppinglistexam.model.entity.Category;
import com.example.shoppinglistexam.model.enums.CategoryEnum;
import com.example.shoppinglistexam.repository.CategoryRepository;
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
                    category.setDescription("Description for " + categoryNameEnum.name());
                    categoryRepository.save(category);
                });
    }

    public Category findByCategoryNameEnum(CategoryEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
