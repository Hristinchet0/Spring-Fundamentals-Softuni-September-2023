package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderByPriceDesc();
}
