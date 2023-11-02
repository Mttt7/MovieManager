package com.mtcompany.moviemanager.service;

import com.mtcompany.moviemanager.entity.Director;

import java.util.List;

public interface DirectorService {

    List<Director> findAll();

    Director findById(int theId);

    Director save(Director theDirector);

    void deleteById(int theId);
}
