package com.mtcompany.moviemanager.dao;

import com.mtcompany.moviemanager.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCategory extends JpaRepository<MovieActor,Integer> {

}
