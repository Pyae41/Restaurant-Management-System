package com.genius.rms.repository;

import com.genius.rms.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByLocale(String locale);
    Language findByLocaleAndLangKey(String locale, String langKey);
}
