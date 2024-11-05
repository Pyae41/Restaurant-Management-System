package com.genius.rms.service.impl;

import com.genius.rms.dto.MenuResponseDto;
import com.genius.rms.exceptions.ResourceNotFoundException;
import com.genius.rms.model.Category;
import com.genius.rms.model.Language;
import com.genius.rms.model.Menu;
import com.genius.rms.dto.MenuRequestDto;
import com.genius.rms.repository.CategoryRepository;
import com.genius.rms.repository.LanguageRepository;
import com.genius.rms.repository.MenuRepository;
import com.genius.rms.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final LanguageRepository languageRepository;
    private final MessageSource messageSource;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    @Override
    public Page<MenuResponseDto> getMenus(Integer page, Integer limit, String sortBy, String lang, Long categoryId) {

        Locale locale = (lang != null) ? new Locale(lang) : Locale.getDefault();
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));

        Page<Menu> menus = null;

        if(categoryId != 0) menus = menuRepository.findByCategoryId(categoryId, pageable);
        else menus = menuRepository.findAll(pageable);


        return menus.map(menu -> {
            String localization =  messageSource.getMessage("menu."+menu.getId(), null, locale);
            return new MenuResponseDto(menu.getId(), localization, menu.getCreatedAt(),menu.getUpdatedAt());
        });
    }

    @Override
    public Menu findById(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Not Found"));
    }

    @Override
    public Menu addMenu(MenuRequestDto menuRequestDto) {

        Menu newMenu = new Menu();
        Category category = categoryRepository.findById(Long.valueOf(menuRequestDto.getCategoryId()))
                        .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        newMenu.setPrice(menuRequestDto.getPrice());
        newMenu.setCategory(category);
        menuRepository.save(newMenu);

        // adding language
        List<String> languages = menuRequestDto.getLanguages();
        languages.forEach(language -> {
            Language lang = new Language();
            lang.setLangKey("menu." + newMenu.getId());
            lang.setLangValue(language.split("-")[1]);
            lang.setLocale(language.split("-")[0]);
            languageRepository.save(lang);
        });

        return newMenu;
    }

    @Override
    public String updateMenu(Long menuId, MenuRequestDto menuRequestDto) {

        Menu updateMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Not Found"));

        Category updateCategory = categoryRepository.findById(Long.valueOf(menuRequestDto.getCategoryId()))
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        // updating language
        String langKey = "menu." + updateMenu.getId();
        List<String> languages = menuRequestDto.getLanguages();
        languages.forEach(language -> {
            String locale = language.split("-")[0];
            Optional<Language> lang = languageRepository.findByLangKeyAndLocale(langKey, locale);

            if(lang.isPresent()){
                Language updateLang = lang.get();
                updateLang.setLangValue(language.split("-")[1]);
                languageRepository.save(updateLang);
            }
        });

        updateMenu.setCategory(updateCategory);
        updateMenu.setPrice(menuRequestDto.getPrice());
        menuRepository.save(updateMenu);

        return "Successfully Updated!";
    }

    @Override
    @Transactional
    public String deleteMenu(Long menuId) {
        Menu deleteMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        // deleting language
        String langCode = "category." + deleteMenu.getId();
        languageRepository.deleteByLangKey(langCode);
        menuRepository.delete(deleteMenu);

        entityManager.flush();

        return "Successfully Deleted";
    }


}
