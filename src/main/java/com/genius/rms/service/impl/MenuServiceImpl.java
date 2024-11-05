package com.genius.rms.service.impl;

import com.genius.rms.model.Menu;
import com.genius.rms.repository.MenuRequestDto;
import com.genius.rms.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public List<Menu> getMenus(Integer page, Integer limit, String sort, String lang) {
        return List.of();
    }

    @Override
    public Menu addMenu(MenuRequestDto menuRequestDto) {
        return null;
    }

    @Override
    public List<Menu> findByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public Menu findById(Long menuId) {
        return null;
    }

    @Override
    public String updateMenu(Long menuId, MenuRequestDto menuRequestDto) {
        return "";
    }

    @Override
    public String deleteMenu(Long menuId) {
        return "";
    }
}
