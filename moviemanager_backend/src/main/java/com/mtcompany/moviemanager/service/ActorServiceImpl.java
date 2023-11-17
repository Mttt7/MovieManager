package com.mtcompany.moviemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public Actor findById(Long theId) {
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
    public void deleteById(Long theId) {
        List<MovieActor> tempMovieActors = movieActorRepository.findByActor_Id(theId);
        tempMovieActors.forEach(mA -> {
            movieActorRepository.delete(mA);
        });
        
        actorRepository.deleteById(theId);
    }

    @Override
    public List<Movie> getMoviesByActorId(Long theId) {
        return this.movieActorRepository.findByActor_Id(theId).stream().map(MovieActor::getMovie).collect(Collectors.toList());
    }

    public List<MovieActor> getAll(){
        return this.movieActorRepository.findAll();
    }

    @Override
    public Actor addMovie(Long actorId, Long movieId) {
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
    public String removeMovieFromActor(Long actorId, Long movieId) {
        Actor tempActor = null;
        Movie tempMovie = null;


        boolean exists = movieActorRepository.existsByActor_IdAndMovie_Id(actorId,movieId);

        if (exists) {
            System.out.println("EXISTS!!!!");
            MovieActor tempMovieActor = movieActorRepository.findByActor_IdAndMovie_Id(actorId,movieId);
            System.out.println(tempMovieActor);
            movieActorRepository.delete(tempMovieActor);
            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.createObjectNode()
                    .put("message", "Movie removed from actor")
                    .put("actorId", actorId)
                    .put("movieId", movieId)
                    .toString();

            return responseJson;
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Actor is no assigned to this movie or has  already been deleted ");
        }

       // return ("Movie id-"+movieId+" removed from actor id-"+actorId);
    }
}
