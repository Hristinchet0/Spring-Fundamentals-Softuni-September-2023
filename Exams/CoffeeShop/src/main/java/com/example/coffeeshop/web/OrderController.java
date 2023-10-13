package com.example.coffeeshop.web;

import com.example.coffeeshop.model.dto.OrderAddDto;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public OrderAddDto orderAddDto() {
        return new OrderAddDto();
    }

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid OrderAddDto orderAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddDto", orderAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDto", bindingResult);

            return "redirect:add";
        }

        orderService.addOrder(modelMapper.map(orderAddDto, OrderServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id){
        orderService.readyOrder(id);

        return "redirect:/";
    }
}
