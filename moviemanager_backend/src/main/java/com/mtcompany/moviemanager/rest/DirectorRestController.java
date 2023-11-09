package com.mtcompany.moviemanager.rest;

import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;
import com.mtcompany.moviemanager.service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DirectorRestController {

    private DirectorService directorService;

    public DirectorRestController(DirectorService directorService) {
        this.directorService = directorService;
    }

    //GET ALL DIRECTORS
    @GetMapping("/directors")
    public List<Director> findAll(){
        return directorService.findAll();
    }

    //GET DIRECTOR BY ID
    @GetMapping("/directors/{directorId}")
    public Director findDirectorById(@PathVariable Long directorId){
        Director theDirector = directorService.findById(directorId);

        if(theDirector == null){
            throw new RuntimeException("Director  id not found - " + directorId);
        }

        return theDirector;
    }

    //ADD NEW DIRECTOR
    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director theDirector){
        Director dbDirector = directorService.save(theDirector);

        return dbDirector;
    }

    //UPDATE DIRECTOR
    @PutMapping("/directors")
    public Director updateDirector(@RequestBody Director theDirector){
        Director dbDirector = directorService.save(theDirector);

        return dbDirector;
    }

    //DELETE DIRECTOR
    @DeleteMapping("/directors/{directorId}")
    public String deleteDirector(@PathVariable Long directorId){
        Director tempDirector = directorService.findById(directorId);

        if (tempDirector == null) {
            throw new RuntimeException("Director id not found - " + directorId);
        }

        directorService.deleteById(directorId);

        return "Deleted Director id - " + directorId;
    }

    //GET MOVIES OF DIRECTOR
    @GetMapping("directors/{directorId}/movies")
    public List<Movie> getMoviesByDirectorId(@PathVariable Long directorId){
        return directorService.getMoviesByDirectorId(directorId);
    }

    //ADD MOVIE TO DIRECTOR
    @PatchMapping("directors/{directorId}/{movieId}")
    public Director addMovieToDirector(@PathVariable Long directorId,@PathVariable Long movieId){
        return directorService.addMovie(directorId,movieId);
    }

    //REMOVE MOVIE FROM DIRECTOR
    @DeleteMapping("directors/{directorId}/{movieId}")
    public String deleteMovieFromDirector(@PathVariable Long directorId,@PathVariable Long movieId){
        return directorService.deleteMovieFromDirector(directorId,movieId);
    }





}
