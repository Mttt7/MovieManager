package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(int theId);

    Movie save(Movie theMovie);

    void deleteById(int theId);



}
