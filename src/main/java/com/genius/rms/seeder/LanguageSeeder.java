package com.genius.rms.seeder;

import com.genius.rms.model.Language;
import com.genius.rms.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class LanguageSeeder implements ApplicationRunner {

    private final LanguageRepository languageRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("language")){
                seedLanguages();
                log.info("Success run user seeder");
            }
            else log.info("Language Seeder Skipped");
        }
    }

    private void seedLanguages(){
        List<Language> languages = new ArrayList<>();

        Language ln1 = new Language();
        ln1.setLocale("en");
        ln1.setLangKey("greeting");
        ln1.setLangValue("Hello");

        Language ln2 = new Language();
        ln2.setLocale("mm");
        ln2.setLangKey("greeting");
        ln2.setLangValue("မင်္ဂလာပါ");

        languages.add(ln1);
        languages.add(ln2);

        languageRepository.saveAll(languages);
        log.info("Success run language seeder");
    }
}
