package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.ActorRepository;
import com.mtcompany.moviemanager.dao.MovieActorRepository;
import com.mtcompany.moviemanager.dao.MovieRepository;
import com.mtcompany.moviemanager.entity.Actor;
import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.entity.MovieActor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;
    private final MovieActorRepository movieActorRepository;
    private final MovieService movieService;




    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(int theId) {
        Optional<Actor> result = actorRepository.findById(theId);

        Actor theActor = null;

        if(result.isPresent()){
            theActor = result.get();
        }else{
            theActor = null;
        }

        return theActor;
    }

    @Override
    public Actor save(Actor theActor) {
        return actorRepository.save(theActor);
    }

    @Override
    public void deleteById(int theId) {
        actorRepository.deleteById(theId);
    }

    @Override
    public List<Movie> getMoviesByActorId(int theId) {
        return this.movieActorRepository.findByActor_Id(theId).stream().map(MovieActor::getMovie).collect(Collectors.toList());
    }


    //do seriwsu movieactor
    public List<MovieActor> getAll(){
        return this.movieActorRepository.findAll();
    }

    @Override
    public Actor addMovie(int actorId, int movieId) {

        Actor tempActor = null;
        Movie tempMovie = null;
        boolean exists = movieActorRepository.existsByActor_IdAndMovie_Id(actorId,movieId);

        if (!exists) {
            tempActor = this.findById(actorId);
            tempMovie = movieService.findById(movieId);

            MovieActor movieActor = new MovieActor(tempActor,tempMovie);
            movieActorRepository.save(movieActor);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Actor already has this movie assigned");
        }

        return tempActor;
    }

    @Override
    public String removeMovieFromActor(int actorId, int movieId) {
        Actor tempActor = null;
        Movie tempMovie = null;
        boolean exists = movieActorRepository.existsByActor_IdAndMovie_Id(actorId,movieId);

        if (exists) {
            System.out.println("EXISTS!!!!");
            MovieActor tempMovieActor = movieActorRepository.findByActor_IdAndMovie_Id(actorId,movieId);
            movieActorRepository.delete(tempMovieActor);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Actor is no assigned to this movie or has  already been deleted ");
        }

        return "Movie id-"+movieId+" removed from actor id-"+actorId;
    }
}