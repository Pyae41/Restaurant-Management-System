package com.genius.rms.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuRequestDto {
    private String menu;
    private String categoryId;
    private Double price;
    private List<String> languages;
}
