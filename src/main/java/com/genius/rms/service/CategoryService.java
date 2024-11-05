package com.genius.rms.service;

import com.genius.rms.dto.CategoryRequestDto;
import com.genius.rms.dto.CategoryResponseDto;
import com.genius.rms.model.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<CategoryResponseDto> getCategories(Integer page, Integer limit, String sortBy, String lang);
    Category getCategory(Long id);
    Category addCategory(CategoryRequestDto categoryRequestDto);
    String updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    String deleteCategory(Long id);
}
