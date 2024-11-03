package com.genius.rms.service;

import com.genius.rms.dto.JwtResponseDto;
import com.genius.rms.dto.LoginDto;
import com.genius.rms.dto.RegisterDto;
import com.genius.rms.model.User;

public interface AuthenticationService {
    User register(RegisterDto registerDto);
    JwtResponseDto login(LoginDto loginDto);
}
