package com.genius.rms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final MessageSource messageSource;

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestParam(name = "lang") String lang){
        Locale locale = (lang != null) ? new Locale(lang) : Locale.getDefault();
        return ResponseEntity.ok(messageSource.getMessage("greeting", null, locale));
    }
}
