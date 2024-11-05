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
@RequestMapping("/api/v1/admin/menu")
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
        try{
            log.info("Success fetching menus");
            return ResponseEntity.ok(menuService.getMenus(page,limit,sort,lang, categoryId));
        }
        catch(Exception e){
            log.info("Fail to fetch menus");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Get one menu
     *
     * @param id
     * @return Menu Object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getCategory(@PathVariable Long id){
        try{
            log.info("Success fetching menu");
            return ResponseEntity.ok(menuService.findById(id));
        }
        catch(Exception e){
            log.error("Fail to fetch menu");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Adding new Category
     *
     * @param menuRequestDto
     * @return Menu Object
     */
    @PostMapping("/add")
    public ResponseEntity<Menu> addCategory(@RequestBody MenuRequestDto menuRequestDto){
        try{
            log.info("Success adding menu");
            return ResponseEntity.ok(menuService.addMenu(menuRequestDto));
        }
        catch(Exception e){
            log.error("Fail to add menu");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
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
        try{
            log.info("Success updating menu");
            return ResponseEntity.ok(menuService.updateMenu(id, menuRequestDto));
        }
        catch(Exception e){
            log.error("Fail to update menu");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Delete menu
     *
     * @param id
     * @return Message Text
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            log.info("Success deleting menu");
            return ResponseEntity.ok(menuService.deleteMenu(id));
        } catch (Exception e) {
            log.error("Fail to delete menu");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }
}
