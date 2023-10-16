package com.example.battleships.web;

import com.example.battleships.model.dto.ShipAddDto;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.service.ShipService;
import com.example.battleships.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public ShipController(ShipService shipService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public ShipAddDto shipAddDto() {
        return new ShipAddDto();
    }

    @GetMapping("/add")
    public String addShip() {

        if(currentUser.getId() == null) {
            return "index";
        }

        return "ship-add";
    }

    @PostMapping("/add")
    public String addShip(@Valid ShipAddDto shipAddDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if(currentUser.getId() == null) {
            return "index";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAddDto", shipAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddDto", bindingResult);

            return "redirect:add";
        }

        shipService.addShip(modelMapper.map(shipAddDto, ShipServiceModel.class));

        return "redirect:/";
    }


}
