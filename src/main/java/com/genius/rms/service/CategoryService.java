package com.genius.rms.service;

import com.genius.rms.dto.CategoryDto;
import com.genius.rms.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
    List<Category> findAll();
    String updateCategory(Long id,CategoryDto categoryDto);
    String deleteCategory(Long id);
}
