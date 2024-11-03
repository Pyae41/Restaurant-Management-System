package com.genius.rms.service.impl;

import com.genius.rms.dto.JwtResponseDto;
import com.genius.rms.dto.LoginDto;
import com.genius.rms.dto.RegisterDto;
import com.genius.rms.model.Role;
import com.genius.rms.model.User;
import com.genius.rms.repository.UserRepository;
import com.genius.rms.service.AuthenticationService;
import com.genius.rms.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User register(RegisterDto registerDto) {
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
    public JwtResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        var user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new  IllegalArgumentException("Invalid username or password"));
        var jwtToken = jwtService.generateToken(user);

        JwtResponseDto jwtResponse = new JwtResponseDto();
        jwtResponse.setToken(jwtToken);

        return jwtResponse;
    }
}
