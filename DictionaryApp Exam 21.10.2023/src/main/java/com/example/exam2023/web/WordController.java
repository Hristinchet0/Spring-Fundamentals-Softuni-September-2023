package com.example.exam2023.web;

import com.example.exam2023.model.dto.WordAddDto;
import com.example.exam2023.model.service.WordServiceModel;
import com.example.exam2023.service.WordService;
import com.example.exam2023.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public WordController(WordService wordService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.wordService = wordService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public WordAddDto wordAddDto() {
        return new WordAddDto();
    }

    @GetMapping("/add")
    public String addTask() {

        if (currentUser.getId() == null) {
            return "index";
        }

        return "word-add";
    }

    @PostMapping("/add")
    public String addTask(@Valid WordAddDto wordAddDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (currentUser.getId() == null) {
            return "index";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddDto", wordAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordAddDto", bindingResult);

            return "redirect:add";
        }

        wordService.addWord(modelMapper.map(wordAddDto, WordServiceModel.class));

        return "redirect:/home";
    }
}
