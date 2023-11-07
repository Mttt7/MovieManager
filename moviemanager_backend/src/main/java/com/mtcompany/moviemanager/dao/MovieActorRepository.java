package com.mtcompany.moviemanager.dao;

import com.mtcompany.moviemanager.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor,Integer> {

    List<MovieActor> findByActor_Id(int id);

    boolean existsByActor_IdAndMovie_Id(int actorId, int movieId);
}
