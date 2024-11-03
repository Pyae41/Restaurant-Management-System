package com.genius.rms.service.impl;

import com.genius.rms.dto.CategoryDto;
import com.genius.rms.model.Category;
import com.genius.rms.repository.CategoryRepository;
import com.genius.rms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        Category newCategory = new Category();
        newCategory.setCategory(categoryDto.getCategory());

        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public String updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Found"));

        category.setCategory(categoryDto.getCategory());
        categoryRepository.save(category);

        return "Successfully Updated!";
    }

    @Override
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Successfully Deleted!";
    }

}
