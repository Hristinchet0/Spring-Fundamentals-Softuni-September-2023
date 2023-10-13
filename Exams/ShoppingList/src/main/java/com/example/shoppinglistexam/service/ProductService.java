package com.example.shoppinglistexam.service;

import com.example.shoppinglistexam.model.dto.ProductViewDto;
import com.example.shoppinglistexam.model.entity.Product;
import com.example.shoppinglistexam.model.enums.CategoryEnum;
import com.example.shoppinglistexam.model.service.ProductServiceModel;
import com.example.shoppinglistexam.repository.ProductRepository;
import com.example.shoppinglistexam.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final CurrentUser currentUser;

    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        product.setUser(userService.findById(currentUser.getId()));
        product.setCategory(categoryService.findByCategoryNameEnum(productServiceModel.getCategory()));

        productRepository.save(product);
    }

    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductViewDto> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDto.class))
                        .collect(Collectors.toList());
    }


    public void buyAllProducts() {
        productRepository.deleteAll();
    }

    public List<ProductViewDto> findAllProductsByCategoryName(CategoryEnum categoryEnum) {
        return productRepository.findByCategory_Name(categoryEnum)
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }

    public Double getTotalPrice() {
        return productRepository.getTotalPrice();
    }
}
