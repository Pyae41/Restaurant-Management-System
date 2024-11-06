package com.genius.rms.controller;

import com.genius.rms.dto.CategoryRequestDto;
import com.genius.rms.dto.CategoryResponseDto;
import com.genius.rms.model.Category;
import com.genius.rms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
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
    public ResponseEntity<Page<CategoryResponseDto>> getCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "en") String lang
    ){
            log.info("Fetching categories");
            Page<CategoryResponseDto> categories = categoryService.getCategories(page,limit,sort,lang);
            log.info("Success fetching categories");
            return ResponseEntity.ok(categories);
    }

    /**
     * get one Category
     *
     * @param id
     * @return Category Object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
            log.info("Fetching category with id");
            Category category = categoryService.getCategory(id);
            log.info("Success fetching category with id");
            return ResponseEntity.ok(category);
    }

    /**
     * Adding new Category
     *
     * @param categoryRequestDto
     * @return Category Object
     */
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
            log.info("Adding category");
            Category category = categoryService.addCategory(categoryRequestDto);
            log.info("Success adding category");
            return ResponseEntity.ok(category);
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
            log.info("Updating category");
            String response = categoryService.updateCategory(id, categoryRequestDto);
            log.info("Success updating category");
            return ResponseEntity.ok(response);
    }

    /**
     * Delete category
     *
     * @param id
     * @return Message Text
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
            log.info("Deleting category");
            String response = categoryService.deleteCategory(id);
            log.info("Success deleting category");
            return ResponseEntity.ok(response);
    }
}
