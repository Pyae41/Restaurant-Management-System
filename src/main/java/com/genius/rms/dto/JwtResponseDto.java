package com.genius.rms.dto;

import com.genius.rms.model.User;
import lombok.Data;

@Data
public class JwtResponseDto {
    private String token;
    private User user;
}
