package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.entity.Director;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category createNewCategory(Category category);
}
