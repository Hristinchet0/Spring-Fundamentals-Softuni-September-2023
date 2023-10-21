package com.example.exam2023.service;

import com.example.exam2023.model.dto.WordViewDto;
import com.example.exam2023.model.entity.Language;
import com.example.exam2023.model.entity.Word;
import com.example.exam2023.model.enums.LanguageNameEnum;
import com.example.exam2023.model.service.WordServiceModel;
import com.example.exam2023.repository.WordRepository;
import com.example.exam2023.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final WordRepository wordRepository;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    private final UserService userService;

    private final LanguageService languageService;

    public WordService(WordRepository wordRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.languageService = languageService;
    }

    public void addWord(WordServiceModel wordServiceModel) {
        Word word = modelMapper.map(wordServiceModel, Word.class);

        word.setAddedBy(userService.findById(currentUser.getId()));
        word.setLanguage(languageService.findByLanguageNameEnum(wordServiceModel.getLanguage()));

        wordRepository.save(word);
    }

    public List<WordViewDto> findAllWordsByLanguage(LanguageNameEnum languageNameEnum) {
        return wordRepository.findByLanguage_Name(languageNameEnum)
                .stream()
                .map(word -> modelMapper.map(word, WordViewDto.class))
                .collect(Collectors.toList());
    }


    public void removeById(Long id) {
        wordRepository.deleteById(id);
    }

    public void removeAll() {
        wordRepository.deleteAll();
    }

    public Long allWordsCount() {
        return wordRepository.count();
    }
}
