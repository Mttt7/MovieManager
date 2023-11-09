package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findById(Long theId);

    Actor save(Actor theActor);

    void deleteById(Long theId);

    public List<Movie> getMoviesByActorId(Long theId);

    public Actor addMovie(Long actorId,Long movieId);

    String removeMovieFromActor(Long actorId, Long movieId);
}
