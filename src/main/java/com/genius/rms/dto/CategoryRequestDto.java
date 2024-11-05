package com.genius.rms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryRequestDto {
    private String category;
    private List<String> languages;
}
