package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.MovieRepository;
import com.mtcompany.moviemanager.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
}
