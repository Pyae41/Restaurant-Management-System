package com.genius.rms.repository;

import com.genius.rms.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByLocale(String locale);
    Optional<Language> findByLangKeyAndLocale(String langKey, String locale);
    void deleteByLangKey(String langKey);
}
