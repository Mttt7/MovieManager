package com.mtcompany.moviemanager.rest;

import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private CategoryService categoryService;
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //GET ALL CATEGORIES
    @GetMapping("/categories")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //GET CATEGORY BY ID
    @GetMapping("/categories/{categoryId}")
    public Category findById(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }

    /*
    //ADD CATEGORY
    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category){
        return categoryService.createNewCategory(category);
    }
    */
}
