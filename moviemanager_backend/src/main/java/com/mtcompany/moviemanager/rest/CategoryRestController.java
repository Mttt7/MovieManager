package com.mtcompany.moviemanager.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.Views;
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
    @JsonView(Views.Summary.class)
    @GetMapping("/categories")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //GET CATEGORY BY ID
    @JsonView(Views.Summary.class)
    @GetMapping("/categories/{categoryId}")
    public Category findById(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }

    @JsonView(Views.Detailed.class)
    @GetMapping("/categories/{categoryId}/movies")
    public Category findByIdWithMovies(@PathVariable Long categoryId){
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
