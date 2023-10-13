package com.example.shoppinglistexam.web;

import com.example.shoppinglistexam.model.dto.ProductViewDto;
import com.example.shoppinglistexam.model.enums.CategoryEnum;
import com.example.shoppinglistexam.service.ProductService;
import com.example.shoppinglistexam.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if(currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("drinks", productService.findAllProductsByCategoryName(CategoryEnum.DRINK));
        model.addAttribute("foods", productService.findAllProductsByCategoryName(CategoryEnum.FOOD));
        model.addAttribute("households", productService.findAllProductsByCategoryName(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", productService.findAllProductsByCategoryName(CategoryEnum.OTHER));
        model.addAttribute("totalPrice", productService.getTotalPrice());

        return "home";
    }




}
