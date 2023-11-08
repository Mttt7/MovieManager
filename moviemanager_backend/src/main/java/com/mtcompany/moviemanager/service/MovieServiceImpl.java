package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.MovieRepository;
import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private CategoryService categoryService;


    public MovieServiceImpl(MovieRepository movieRepository, CategoryService categoryService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int theId) {
        Optional<Movie> result = movieRepository.findById(theId);

        Movie theMovie = null;

        if(result.isPresent()){
            theMovie = result.get();
        }else{
            theMovie = null;
        }

        return theMovie;
    }

    @Override
    public Movie save(Movie theMovie) {
        return movieRepository.save(theMovie);
    }

    @Override
    public void deleteById(int theId) {
        movieRepository.deleteById(theId);
    }

    @Override
    public Movie addCategoryToMovie(int movieId, int categoryId) {
        Movie tempMovie = this.findById(movieId);
        Category tempCategory = categoryService.findById(categoryId);

        tempMovie.setCategoryId((long) tempCategory.getId());
        movieRepository.save(tempMovie);

        return tempMovie;
    }


}
