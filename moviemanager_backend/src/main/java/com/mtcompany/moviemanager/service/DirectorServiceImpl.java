package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.DirectorRepository;
import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService{


    private DirectorRepository directorRepository;
    private MovieService movieService;

    public DirectorServiceImpl(DirectorRepository directorRepository, MovieService movieService) {
        this.directorRepository = directorRepository;
        this.movieService = movieService;
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(int theId) {
        Optional<Director> result = directorRepository.findById(theId);

        Director theDirector = null;

        if(result.isPresent()){
            theDirector = result.get();
        }else{
            theDirector = null;
        }

        return theDirector;
    }

    @Override
    public Director save(Director theDirector) {
        return directorRepository.save(theDirector);
    }

    @Override
    public void deleteById(int theId) {

        directorRepository.deleteById(theId);
    }


    public List<Movie> getMoviesByDirectorId(int theId){
        Director tempDirector = findById(theId); // add this.?

        List<Movie> movies = tempDirector.getMovies();

        return movies;
    }

    @Override
    public Director addMovie(int directorId, int movieId) {
        Director tempDirector = this.findById(directorId);
        Movie tempMovie = movieService.findById(movieId);

        tempDirector.addMovie(tempMovie);
        directorRepository.save(tempDirector);

        return tempDirector;
    }

    @Override
    public String deleteMovieFromDirector(int directorId, int movieId) {
        Director tempDirector = this.findById(directorId);
        Movie tempMovie = movieService.findById(movieId);

        boolean exists = tempDirector.getMovies().stream().anyMatch(m ->m==tempMovie);

        if(exists){
            tempDirector.deleteMovie(tempMovie);
            tempMovie.setDirectorId(null);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found or has already been deleted");
        }

        directorRepository.save(tempDirector);

        return "Movie id-"+movieId+" deleted from director id-"+directorId;
    }


}
