package com.genius.rms.service;

import com.genius.rms.model.Menu;
import com.genius.rms.repository.MenuRequestDto;

import java.util.List;

public interface MenuService {
    List<Menu> getMenus(Integer page, Integer limit, String sort, String lang);
    Menu addMenu(MenuRequestDto menuRequestDto);
    List<Menu> findByCategory(Long categoryId);
    Menu findById(Long menuId);
    String updateMenu(Long menuId,MenuRequestDto MenuRequestDto);
    String deleteMenu(Long menuId);
}
