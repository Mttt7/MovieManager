package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Movie;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findById(int theId);

    Actor save(Actor theActor);

    void deleteById(int theId);

    public List<Movie> getMoviesByActorId(int theId);

    public Actor addMovie(int actorId,int movieId);

    String removeMovieFromActor(int actorId, int movieId);
}
