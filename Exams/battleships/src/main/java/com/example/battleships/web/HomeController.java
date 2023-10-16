package com.example.battleships.web;

import com.example.battleships.model.dto.ShipDto;
import com.example.battleships.model.dto.StartBattleDto;
import com.example.battleships.service.ShipService;
import com.example.battleships.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;

    private final CurrentUser currentUser;

    public HomeController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("startBattleDto")
    public StartBattleDto startBattleDto() {
        return new StartBattleDto();
    }

    @GetMapping("/")
    public String home(Model model) {

        if(currentUser.getId() == null) {
            return "index";
        }

        long userId = currentUser.getId();

        List<ShipDto> ownShips = shipService.getOwnedShips(userId);
        List<ShipDto> enemyShips = shipService.getNotOwnedShips(userId);
        List<ShipDto> sortedShips = shipService.getAllSortedShips();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDto startBattleDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if(currentUser.getId() == null) {
            return "index";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDto", startBattleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDto", bindingResult);

            return "redirect:/home";
        }

        shipService.attack(startBattleDto);


        return "home";
    }


}
