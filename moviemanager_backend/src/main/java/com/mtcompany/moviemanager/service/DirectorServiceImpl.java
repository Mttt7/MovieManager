package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.dao.DirectorRepository;
import com.mtcompany.moviemanager.entity.Director;
import com.mtcompany.moviemanager.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService{


    private DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
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
}
