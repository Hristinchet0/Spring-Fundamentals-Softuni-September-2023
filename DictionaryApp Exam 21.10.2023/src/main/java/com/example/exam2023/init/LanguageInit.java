package com.example.exam2023.init;

import com.example.exam2023.service.LanguageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LanguageInit implements CommandLineRunner {

    private final LanguageService languageService;

    public LanguageInit(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public void run(String... args) throws Exception {
        languageService.initLanguage();
    }
}
