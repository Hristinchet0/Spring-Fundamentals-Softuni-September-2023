package com.example.exam2023.repository;

import com.example.exam2023.model.entity.Word;
import com.example.exam2023.model.enums.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByLanguage_Name(LanguageNameEnum languageNameEnum);

}
