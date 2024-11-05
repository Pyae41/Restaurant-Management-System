package com.genius.rms.service;

import com.genius.rms.dto.JwtResponseDto;
import com.genius.rms.dto.LoginRequestDto;
import com.genius.rms.dto.RegisterRequestDto;
import com.genius.rms.model.User;

public interface AuthenticationService {
    User register(RegisterRequestDto registerDto);
    JwtResponseDto login(String lang,LoginRequestDto loginRequestDto);
}
