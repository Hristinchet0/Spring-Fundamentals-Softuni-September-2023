package com.example.exam2023.model.dto;

import com.example.exam2023.model.enums.LanguageNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class WordAddDto {

    @NotNull
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;

    @NotNull
    @Size(min = 2, max = 80, message = "The translation must be between 2 and 80 characters!")
    private String translation;

    @NotNull
    @Size(min = 2, max = 200, message = "The example must be between 2 and 200 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in past or present!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputDate;

    @NotNull(message = "You must select language!")
    private LanguageNameEnum language;

    public WordAddDto() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageNameEnum language) {
        this.language = language;
    }
}
