package com.genius.rms.dto;

import com.genius.rms.model.User;
import lombok.Data;

import java.util.Map;

@Data
public class JwtResponseDto {
    private String token;
    private User user;
    private Map<String, Map<String,String>> systemLanguages;
}
