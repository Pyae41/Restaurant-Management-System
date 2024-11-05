package com.genius.rms.utils;

import com.genius.rms.model.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, Map<String, String>> getSystemLanguages(List<Language> languages){
        Map<String,Map<String,String>> rLanguage = new HashMap<>();
        Map<String, String> iLanguage = new HashMap<>();
        languages.forEach(language -> {
            if(language.getLangKey().contains("system"))
                iLanguage.put(language.getLangKey(), language.getLangValue());
        });

        rLanguage.put("languages", iLanguage);
        return rLanguage;
    }
}
