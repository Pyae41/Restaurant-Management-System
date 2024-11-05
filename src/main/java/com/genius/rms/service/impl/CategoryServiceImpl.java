package com.genius.rms.service.impl;

import com.genius.rms.dto.CategoryRequestDto;
import com.genius.rms.exceptions.ResourceNotFoundException;
import com.genius.rms.model.Category;
import com.genius.rms.model.Language;
import com.genius.rms.repository.CategoryRepository;
import com.genius.rms.repository.LanguageRepository;
import com.genius.rms.service.CategoryService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final MessageSource messageSource;
    private final EntityManager entityManager;

    @Override
    public Page<Category> getCategories(Integer page, Integer limit, String sortBy, String lang){
        Locale locale = (lang != null) ? new Locale(lang) : Locale.getDefault();
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));

        Page<Category> categories = categoryRepository.findAll(pageable);

        categories.map(category -> {
            String localization =  messageSource.getMessage("category."+category.getId(), null, locale);
            category.setCategory(localization);
            return category;
        });

        return categories;
    }

    @Override
    public Category getCategory(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public Category addCategory(CategoryRequestDto categoryRequestDto) {

        Category newCategory = new Category();
        newCategory.setCategory(categoryRequestDto.getCategory());
        categoryRepository.save(newCategory);

        // adding language
        List<String> languages = categoryRequestDto.getLanguages();
        languages.forEach(language -> {
            Language lang = new Language();
            lang.setLangKey("category." + newCategory.getId());
            lang.setLangValue(language.split("-")[1]);
            lang.setLocale(language.split("-")[0]);
            languageRepository.save(lang);
        });

        return newCategory;
    }

    @Override
    public String updateCategory(Long id, CategoryRequestDto categoryRequestDto) {

        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        // updating language
        String langKey = "category." + updateCategory.getId();
        List<String> languages = categoryRequestDto.getLanguages();
        languages.forEach(language -> {
            String locale = language.split("-")[0];
            Optional<Language> lang = languageRepository.findByLangKeyAndLocale(langKey, locale);
            System.out.println("update lang{}" + lang   );

            if(lang.isPresent()){
                Language updateLang = lang.get();
                updateLang.setLangValue(language.split("-")[1]);
                languageRepository.save(updateLang);
            }
        });

        return "Successfully Updated!";
    }

    @Override
    @Transactional
    public String deleteCategory(Long id) {
        Category deleteCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        // deleting language
        String langCode = "category." + deleteCategory.getId();
        languageRepository.deleteByLangKey(langCode);
        categoryRepository.delete(deleteCategory);

        entityManager.flush();
        return "Successfully Deleted!";
    }
}
