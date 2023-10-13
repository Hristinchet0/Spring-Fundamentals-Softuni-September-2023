package com.example.shoppinglistexam.web;

import com.example.shoppinglistexam.model.dto.ProductAddDto;
import com.example.shoppinglistexam.model.service.ProductServiceModel;
import com.example.shoppinglistexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ProductAddDto productAddDto() {
        return new ProductAddDto();
    }

    @GetMapping("/add")
    public String addProduct() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddDto productAddDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddDto", productAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddDto", bindingResult);

            return "redirect:add";
        }

        productService.addProduct(modelMapper.map(productAddDto, ProductServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id) {
        productService.buyProduct(id);

        return "redirect:/";
    }

    @GetMapping("/buyAll")
    public String buyAll() {
        productService.buyAllProducts();

        return "redirect:/";
    }
}

