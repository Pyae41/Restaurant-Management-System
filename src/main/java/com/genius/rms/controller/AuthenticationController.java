package com.genius.rms.controller;

import com.genius.rms.dto.JwtResponseDto;
import com.genius.rms.dto.LoginRequestDto;
import com.genius.rms.dto.RegisterRequestDto;
import com.genius.rms.model.User;
import com.genius.rms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerDto){
            log.info("Registering user");
            User user = authenticationService.register(registerDto);
            log.info("Success register user");
            return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(
            @RequestParam(defaultValue = "en") String lang,
            @RequestBody LoginRequestDto loginRequestDto){
        log.info("Login into account");
        JwtResponseDto jwtResponseDto = authenticationService.login(lang, loginRequestDto);
        log.info("Success login");
        return ResponseEntity.ok(jwtResponseDto);
    }
}
