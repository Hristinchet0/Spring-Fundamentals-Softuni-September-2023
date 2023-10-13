package com.example.shoppinglistexam.repository;

import com.example.shoppinglistexam.model.dto.ProductViewDto;
import com.example.shoppinglistexam.model.entity.Product;
import com.example.shoppinglistexam.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Name(CategoryEnum categoryEnum);

    @Query("SELECT SUM(p.price) FROM Product p")
    Double getTotalPrice();
}
