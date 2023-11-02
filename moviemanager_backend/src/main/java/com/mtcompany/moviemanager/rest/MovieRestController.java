package com.mtcompany.moviemanager.rest;


import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    private MovieService movieService;

    @Autowired
    public MovieRestController(MovieService theMovieService) {
        movieService = theMovieService;
    }

    //GET ALL MOVIES
    @GetMapping("/movies")
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    //GET MOVIE BY ID
    @GetMapping("/movies/{movieId}")
    public Movie findMovieById(@PathVariable int movieId){
        Movie theMovie = movieService.findById(movieId);

        if(theMovie == null){
            throw new RuntimeException("Movie  id not found - " + movieId);
        }


        return theMovie;
    }

    //ADD NEW MOVIE
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie theMovie){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theMovie.setId(0);

        Movie dbMovie = movieService.save(theMovie);
        return dbMovie;
    }

    //UPDATE MOVIE
    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie theMovie){
        Movie dbMovie = movieService.save(theMovie);

        return dbMovie;
    }

    //DELETE MOVIE

    @DeleteMapping("/movies/{movieId}")
    public String deleteMovie(@PathVariable int movieId){
        Movie tempMovie = movieService.findById(movieId);

        if (tempMovie == null) {
            throw new RuntimeException("Movie id not found - " + movieId);
        }

        movieService.deleteById(movieId);

        return "Deleted Movie id - " + movieId;
    }

}
