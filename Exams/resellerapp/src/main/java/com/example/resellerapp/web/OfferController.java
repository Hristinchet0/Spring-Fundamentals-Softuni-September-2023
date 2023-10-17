package com.example.resellerapp.web;

import com.example.resellerapp.model.dto.OfferAddDto;
import com.example.resellerapp.model.service.OfferServiceModel;
import com.example.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public OfferAddDto offerAddDto() {
        return new OfferAddDto();
    }

    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid OfferAddDto offerAddDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDto", offerAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddDto", bindingResult);

            return "redirect:add";
        }

        offerService.addProduct(modelMapper.map(offerAddDto, OfferServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/buy-offer/{id}")
    public String buyOffer(@PathVariable Long id) {
        offerService.buyOfferWithId(id);
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable Long id) {
        offerService.removeOfferById(id);
        return "redirect:/";
    }


}
