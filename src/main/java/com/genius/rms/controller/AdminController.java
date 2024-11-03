package com.genius.rms.controller;

import com.genius.rms.dto.RegisterDto;
import com.genius.rms.model.User;
import com.genius.rms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {

    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        log.info("Success adding user");
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestParam(name = "lang") String lang){
        Locale locale = (lang != null) ? new Locale(lang) : Locale.getDefault();
        return ResponseEntity.ok(messageSource.getMessage("greeting", null, locale));
    }
}
