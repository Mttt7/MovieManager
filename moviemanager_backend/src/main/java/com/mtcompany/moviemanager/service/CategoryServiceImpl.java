package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.CategoryRepository;
import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.entity.Director;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long theId) {
        Optional<Category> result = categoryRepository.findById(theId);

        Category theCategory = null;

        if(result.isPresent()){
            theCategory = result.get();
        }else{
            theCategory = null;
        }

        return theCategory;

    }

    @Override
    public Category createNewCategory(Category category) {
        return categoryRepository.save(category);
    }
}
