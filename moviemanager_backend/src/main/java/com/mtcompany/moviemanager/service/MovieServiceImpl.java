package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.MovieActorRepository;
import com.mtcompany.moviemanager.dao.MovieRepository;
import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Category;
import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.entity.MovieActor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private MovieActorRepository movieActorRepository;
    private CategoryService categoryService;


    public MovieServiceImpl(MovieRepository movieRepository, MovieActorRepository movieActorRepository, CategoryService categoryService) {
        this.movieRepository = movieRepository;
        this.movieActorRepository = movieActorRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long theId) {
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
    public void deleteById(Long theId) {
        movieRepository.deleteById(theId);
    }

    @Override
    public Movie addCategoryToMovie(Long movieId, Long categoryId) {
        Movie tempMovie = this.findById(movieId);
        Category tempCategory = categoryService.findById(categoryId);

        tempMovie.setCategoryId((long) tempCategory.getId());
        movieRepository.save(tempMovie);

        return tempMovie;
    }

    @Override
    public List<Actor> getActorsByMovieId(Long movieId) {
        return this.movieActorRepository.findByMovie_Id(movieId).stream().map(MovieActor::getActor).collect(Collectors.toList());
    }


}
