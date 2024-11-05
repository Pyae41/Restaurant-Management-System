package com.genius.rms.service;

import com.genius.rms.dto.MenuResponseDto;
import com.genius.rms.model.Menu;
import com.genius.rms.dto.MenuRequestDto;
import org.springframework.data.domain.Page;

public interface MenuService {
    Page<MenuResponseDto> getMenus(Integer page, Integer limit, String sort, String lang, Long categoryId);
    Menu addMenu(MenuRequestDto menuRequestDto);
    Menu findById(Long menuId);
    String updateMenu(Long menuId,MenuRequestDto menuRequestDto);
    String deleteMenu(Long menuId);
}
