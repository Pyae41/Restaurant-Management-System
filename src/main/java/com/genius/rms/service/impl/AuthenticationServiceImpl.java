package com.genius.rms.service.impl;

import com.genius.rms.dto.JwtResponseDto;
import com.genius.rms.dto.LoginRequestDto;
import com.genius.rms.dto.RegisterRequestDto;
import com.genius.rms.exceptions.ResourceNotFoundException;
import com.genius.rms.model.Language;
import com.genius.rms.repository.LanguageRepository;
import com.genius.rms.utils.Role;
import com.genius.rms.model.User;
import com.genius.rms.repository.UserRepository;
import com.genius.rms.service.AuthenticationService;
import com.genius.rms.service.JwtService;
import com.genius.rms.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final LanguageRepository languageRepository;

    @Override
    public User register(RegisterRequestDto registerDto) {
        User user = new User();

        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);
        if(!registerDto.getEmail().isEmpty() &&
        registerDto.getEmail().split("@")[1].contains("admin")){
            user.setRole(Role.ADMIN);
        }

        return userRepository.save(user);
    }

    @Override
    public JwtResponseDto login(String lang, LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        ));

        var user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));
        var jwtToken = jwtService.generateToken(user);

        // Load System Languages
        List<Language> languages = languageRepository.findByLocale(lang);

        JwtResponseDto jwtResponse = new JwtResponseDto();
        jwtResponse.setToken(jwtToken);
        jwtResponse.setUser(user);
        jwtResponse.setSystemLanguages(Utils.getSystemLanguages(languages));

        return jwtResponse;
    }
}
