package com.genius.rms.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LanguageSourceConfig {

    @Bean
    public MessageSource messageSource(DBLanguageSource dbLanguageSource){
        return dbLanguageSource;
    }
}
