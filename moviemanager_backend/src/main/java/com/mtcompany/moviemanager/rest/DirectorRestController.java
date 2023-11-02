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
    public Director findDirectorById(@PathVariable int directorId){
        Director theDirector = directorService.findById(directorId);

        if(theDirector == null){
            throw new RuntimeException("Director  id not found - " + directorId);
        }

        return theDirector;
    }

    //ADD NEW DIRECTOR
    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director theDirector){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theDirector.setId(0);

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
    public String deleteDirector(@PathVariable int directorId){
        Director tempDirector = directorService.findById(directorId);

        if (tempDirector == null) {
            throw new RuntimeException("Director id not found - " + directorId);
        }
        directorService.deleteById(directorId);
        return "Deleted Director id - " + directorId;
    }





}
