package com.genius.rms.controller;

import com.genius.rms.dto.MenuRequestDto;
import com.genius.rms.dto.MenuResponseDto;
import com.genius.rms.model.Menu;
import com.genius.rms.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * Fetch all menus
     *
     * @param page
     * @param limit
     * @param sort
     * @param categoryId
     * @return Menus List
     */
    @GetMapping
    public ResponseEntity<Page<MenuResponseDto>> getCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "0") Long categoryId
    ){
            log.info("Fetching menus");
            Page<MenuResponseDto> menus = menuService.getMenus(page,limit,sort,lang, categoryId);
            log.info("Success fetching menus");
            return ResponseEntity.ok(menus);
    }

    /**
     * Get one menu
     *
     * @param id
     * @return Menu Object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getCategory(@PathVariable Long id){
            log.info("Fetching menu with id");
            Menu menu = menuService.findById(id);
            log.info("Success fetching menu with id");
            return ResponseEntity.ok(menu);
    }

    /**
     * Adding new Category
     *
     * @param menuRequestDto
     * @return Menu Object
     */
    @PostMapping("/add")
    public ResponseEntity<Menu> addCategory(@RequestBody MenuRequestDto menuRequestDto){
            log.info("Adding menu");
            Menu menu = menuService.addMenu(menuRequestDto);
            log.info("Success adding menu");
            return ResponseEntity.ok(menu);
    }

    /**
     * Update Existing Menu
     *
     * @param id
     * @param menuRequestDto
     * @return Message Text
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody MenuRequestDto menuRequestDto){
            log.info("Updating menu");
            String response = menuService.updateMenu(id, menuRequestDto);
            log.info("Success updating menu");
            return ResponseEntity.ok(response);
    }

    /**
     * Delete menu
     *
     * @param id
     * @return Message Text
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
            log.info("Deleting menu");
            String response = menuService.deleteMenu(id);
            log.info("Success deleting menu");
            return ResponseEntity.ok(response);
    }
}
