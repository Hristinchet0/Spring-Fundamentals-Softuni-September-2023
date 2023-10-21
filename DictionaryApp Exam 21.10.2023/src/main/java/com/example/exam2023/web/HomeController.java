package com.example.exam2023.web;

import com.example.exam2023.model.dto.WordViewDto;
import com.example.exam2023.model.enums.LanguageNameEnum;
import com.example.exam2023.service.WordService;
import com.example.exam2023.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping(value = {"/index", "/"})
    private String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (currentUser.getId() == null) {
            return "index";
        }

        List<WordViewDto> germanWordsList = wordService.findAllWordsByLanguage(LanguageNameEnum.GERMAN);
        List<WordViewDto> spanishWordsList = wordService.findAllWordsByLanguage(LanguageNameEnum.SPANISH);
        List<WordViewDto> frenchWordsList = wordService.findAllWordsByLanguage(LanguageNameEnum.FRENCH);
        List<WordViewDto> italianWordsList = wordService.findAllWordsByLanguage(LanguageNameEnum.ITALIAN);

        Long allWordsCount = wordService.allWordsCount();

        model.addAttribute("germanWordsList", germanWordsList);
        model.addAttribute("spanishWordsList", spanishWordsList);
        model.addAttribute("frenchWordsList", frenchWordsList);
        model.addAttribute("italianWordsList", italianWordsList);
        model.addAttribute("allWordsCount", allWordsCount);

        return "home";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {

        if (currentUser.getId() == null) {
            return "index";
        }

        wordService.removeById(id);

        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {

        if (currentUser.getId() == null) {
            return "index";
        }

        wordService.removeAll();

        return "redirect:/home";
    }
}
