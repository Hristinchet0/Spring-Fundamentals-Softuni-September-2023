package com.example.coffeeshop.web;

import com.example.coffeeshop.model.dto.OrderViewDto;
import com.example.coffeeshop.model.dto.UserViewDto;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final OrderService orderService;

    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    private String index(Model model) {

        if(currentUser.getId() == null) {
            return "index";
        }

        List<OrderViewDto> orders = orderService.findAllOrderByPriceDesc();

        Integer totalTime = orders.stream()
                .map(orderViewDto -> orderViewDto.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0);

        List<UserViewDto> users = userService.findAllUserAndCountOfOrdersByCountDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", totalTime);
        model.addAttribute("users", users);

        return "home";
    }


}
