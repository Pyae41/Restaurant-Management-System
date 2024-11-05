package com.genius.rms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryRequestDto {
    private List<String> languages;
}
