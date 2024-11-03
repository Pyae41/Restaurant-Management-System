package com.genius.rms.controller;

import com.genius.rms.dto.RegisterDto;
import com.genius.rms.model.User;
import com.genius.rms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @GetMapping()
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("Hi Admin");
    }
}
