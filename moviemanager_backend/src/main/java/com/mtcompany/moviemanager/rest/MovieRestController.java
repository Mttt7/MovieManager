package com.mtcompany.moviemanager.rest;


import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.entity.MovieActor;
import com.mtcompany.moviemanager.service.ActorService;
import com.mtcompany.moviemanager.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    private MovieService movieService;
    private ActorService actorService;

    @Autowired
    public MovieRestController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    //GET ALL MOVIES
    @GetMapping("/movies")
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    //GET MOVIE BY ID
    @GetMapping("/movies/{movieId}")
    public Movie findMovieById(@PathVariable Long movieId){
        Movie theMovie = movieService.findById(movieId);

        if(theMovie == null){
            throw new RuntimeException("Movie  id not found - " + movieId);
        }


        return theMovie;
    }

    //ADD NEW MOVIE
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie theMovie){

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
    public void deleteMovie(@PathVariable Long movieId){
        Movie tempMovie = movieService.findById(movieId);

        if (tempMovie == null) {
            throw new RuntimeException("Movie id not found - " + movieId);
        }

        List<Actor> actors = movieService.getActorsByMovieId(movieId);
        for (Actor a:
             actors) {
            actorService.removeMovieFromActor(a.getId(),movieId);
        }
        movieService.deleteById(movieId);

        //return "Deleted Movie id - " + movieId;
    }

    //ADD CATEGORY TO A MOVIE
    @PatchMapping("movies/{movieId}/{categoryId}")
    public Movie addCategoryToMovie(@PathVariable Long movieId, @PathVariable Long categoryId){
        return movieService.addCategoryToMovie(movieId,categoryId);
    }

    //GET ACTORS FEATURED IN MOVIE
    @GetMapping("/movies/{movieId}/actors")
    public List<Actor> getActorsByMovieId(@PathVariable Long movieId){
        return this.movieService.getActorsByMovieId(movieId);
    }




}
