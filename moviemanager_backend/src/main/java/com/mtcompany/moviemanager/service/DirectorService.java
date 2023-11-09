package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface DirectorService {

    List<Director> findAll();

    Director findById(Long theId);

    Director save(Director theDirector);

    void deleteById(Long theId);

    public List<Movie> getMoviesByDirectorId(Long theId);

    public Director addMovie(Long directorId,Long movieId);

    String deleteMovieFromDirector(Long directorId, Long movieId);
}
