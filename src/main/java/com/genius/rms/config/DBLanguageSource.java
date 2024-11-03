package com.genius.rms.config;


import com.genius.rms.repository.LanguageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DBLanguageSource extends AbstractMessageSource {

    private final LanguageRepository languageRepository;

    // Cache languages for efficiency
    private final Map<String, Map<String, String>> languageCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadLanguages(){
        // load languages from the database at startup
        languageRepository.findAll().forEach(language -> {
            languageCache
                    .computeIfAbsent(language.getLocale(), k -> new ConcurrentHashMap<>())
                    .put(language.getLangKey(), language.getLangValue());
        });
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        Map<String, String> langForLocale = languageCache.get(locale.getLanguage());
        if(!langForLocale.isEmpty() && langForLocale.containsKey(code)){
            return new MessageFormat(langForLocale.get(code), locale);
        }

        // Fallback message if key not found
        return new MessageFormat("Message not found: " + code, locale);
    }
}
