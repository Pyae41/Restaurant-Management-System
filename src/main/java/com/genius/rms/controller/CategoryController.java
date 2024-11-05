package com.genius.rms.controller;

import com.genius.rms.dto.CategoryRequestDto;
import com.genius.rms.model.Category;
import com.genius.rms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Fetch all categories
     *
     * @param page
     * @param limit
     * @param sort
     * @return Category List
     */
    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "en") String lang
    ){
        try{
            log.info("Success fetching categories");
            return ResponseEntity.ok(categoryService.getCategories(page,limit,sort,lang));
        }
        catch(Exception e){
            log.info("Fail to fetch categories");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * get one Category
     *
     * @param id
     * @return Category Object
     */
    @PostMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        try{
            log.info("Success fetching category");
            return ResponseEntity.ok(categoryService.getCategory(id));
        }
        catch(Exception e){
            log.error("Fail to fetch category");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Adding new Category
     *
     * @param categoryRequestDto
     * @return Category Object
     */
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        try{
            log.info("Success adding category");
            return ResponseEntity.ok(categoryService.addCategory(categoryRequestDto));
        }
        catch(Exception e){
            log.error("Fail to add category");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Update Existing Category
     *
     * @param id
     * @param categoryRequestDto
     * @return Message Text
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto){
        try{
            log.info("Success updating category");
            return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDto));
        }
        catch(Exception e){
            log.error("Fail to update category");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }

    /**
     * Delete category
     *
     * @param id
     * @return Message Text
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            log.info("Success deleting category");
            return ResponseEntity.ok(categoryService.deleteCategory(id));
        } catch (Exception e) {
            log.error("Fail to delete category");
            throw new RuntimeException("Error: {}" + e.getMessage());
        }
    }
}
