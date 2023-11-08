package com.mtcompany.moviemanager.rest;

import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Actor;
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
    public Actor findActorById(@PathVariable int actorId){
        Actor tempActor = actorService.findById(actorId);
        if(tempActor == null){
            throw new RuntimeException("Actor  id not found - " + actorId);
        }

        return tempActor;
    }

    //ADD NEW ACTOR
    @PostMapping("/actors")
    public Actor addActor(@RequestBody Actor theActor){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theActor.setId(0);

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
    public String deleteActor(@PathVariable int actorId){
        Actor tempActor = actorService.findById(actorId);

        if (tempActor == null) {
            throw new RuntimeException("Actor id not found - " + actorId);
        }
        actorService.deleteById(actorId);
        return "Deleted Actor id - " + actorId;
    }

    //ADD MOVIE TO AN ACTOR
    @PatchMapping("/actors/{actorId}/{movieId}")
    public Actor addMovieToActor(@PathVariable int actorId, @PathVariable int movieId){
        return actorService.addMovie(actorId,movieId);
    }

    //REMOVE MOVIE FROM AN ACTOR
    @DeleteMapping("/actors/{actorId}/{movieId}")
    public String removeMovieFromActor(@PathVariable int actorId, @PathVariable int movieId){
        return actorService.removeMovieFromActor(actorId,movieId);
    }

}
