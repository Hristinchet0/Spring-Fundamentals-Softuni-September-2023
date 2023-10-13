package com.example.shoppinglistexam.web;

import com.example.shoppinglistexam.model.dto.UserLoginDto;
import com.example.shoppinglistexam.model.dto.UserRegisterDto;
import com.example.shoppinglistexam.model.service.UserServiceModel;
import com.example.shoppinglistexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @ModelAttribute
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/register")
    private String register() {
        return "register";
    }

    @PostMapping("/register")
    private String register(@Valid UserRegisterDto userRegisterDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterDto, UserServiceModel.class));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel =
                userService.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userLoginDto.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }
}
