package com.example.exam2023.repository;

import com.example.exam2023.model.entity.Language;
import com.example.exam2023.model.enums.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findByName(LanguageNameEnum language);
}
