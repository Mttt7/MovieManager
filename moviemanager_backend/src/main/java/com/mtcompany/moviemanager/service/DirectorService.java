package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface DirectorService {

    List<Director> findAll();

    Director findById(int theId);

    Director save(Director theDirector);

    void deleteById(int theId);

    public List<Movie> getMoviesByDirectorId(int theId);

    public Director addMovie(int directorId,int movieId);
}
