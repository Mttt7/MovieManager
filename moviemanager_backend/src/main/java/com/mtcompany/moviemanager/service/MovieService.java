package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(Long theId);

    Movie save(Movie theMovie);

    void deleteById(Long theId);


    Movie addCategoryToMovie(Long movieId, Long categoryId);
}
