package com.example.resellerapp.web;

import com.example.resellerapp.model.dto.OfferViewDto;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final OfferService offerService;

    public HomeController(CurrentUser currentUser, OfferService offerService) {
        this.currentUser = currentUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if(currentUser.getId() == null) {
            return "index";
        }

        Long currentUserId = currentUser.getId();

        List<OfferViewDto> myOffers = offerService.myOffers(currentUserId);
        List<OfferViewDto> boughtItems = offerService.boughtItems(currentUserId);
        List<OfferViewDto> otherOffers = offerService.otherOffers(currentUserId);

        model.addAttribute("myOffers", myOffers);
        model.addAttribute("boughtItems", boughtItems);
        model.addAttribute("otherOffers", otherOffers);
        model.addAttribute("currentUser", currentUser);

        return "home";
    }




}
