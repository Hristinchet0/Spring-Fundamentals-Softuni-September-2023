package com.example.coffeeshop.service;

import com.example.coffeeshop.model.dto.OrderViewDto;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CurrentUser currentUser;

    private final UserService userService;

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, CurrentUser currentUser, UserService userService, CategoryService categoryService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryNameEnum(orderServiceModel.getCategory()));

        orderRepository.save(order);
    }

    public List<OrderViewDto> findAllOrderByPriceDesc() {
        return orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> modelMapper.map(order, OrderViewDto.class))
                .collect(Collectors.toList());
    }

    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
