package com.mtcompany.moviemanager.rest;

import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActorRestController {

    private ActorService actorService;

    public ActorRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    //GET ALL ACTORS
    @GetMapping("/actors")
    public List<Actor> findAll(){
        return actorService.findAll();
    }

    //GET ACTOR BY ID
    @GetMapping("/actors/{actorId}")
    public Actor findActorById(@PathVariable Long actorId){
        Actor tempActor = actorService.findById(actorId);
        if(tempActor == null){
            throw new RuntimeException("Actor  id not found - " + actorId);
        }

        return tempActor;
    }

    //ADD NEW ACTOR
    @PostMapping("/actors")
    public Actor addActor(@RequestBody Actor theActor){
        Actor dbActor = actorService.save(theActor);
        return dbActor;
    }

    //UPDATE ACTOR
    @PutMapping("/actors")
    public Actor updateActor(@RequestBody Actor theActor){
        Actor dbActor = actorService.save(theActor);
        return dbActor;
    }

    //DELETE ACTOR
    @DeleteMapping("/actors/{actorId}")
    public String deleteActor(@PathVariable Long actorId){
        Actor tempActor = actorService.findById(actorId);

        if (tempActor == null) {
            throw new RuntimeException("Actor id not found - " + actorId);
        }
        actorService.deleteById(actorId);
        return "Deleted Actor id - " + actorId;
    }

    //GET MOVIES ASSOCIATED TO ACTOR
    @GetMapping("/actors/{actorId}/movies")
    public List<Movie> getMoviesByActorId(@PathVariable Long actorId){
        return this.actorService.getMoviesByActorId(actorId);
    }

    //ADD MOVIE TO AN ACTOR
    @PatchMapping("/actors/{actorId}/{movieId}")
    public Actor addMovieToActor(@PathVariable Long actorId, @PathVariable Long movieId){
        return actorService.addMovie(actorId,movieId);
    }

    //REMOVE MOVIE FROM AN ACTOR
    @DeleteMapping("/actors/{actorId}/{movieId}")
    public String removeMovieFromActor(@PathVariable Long actorId, @PathVariable Long movieId){
        return actorService.removeMovieFromActor(actorId,movieId);
    }
}
