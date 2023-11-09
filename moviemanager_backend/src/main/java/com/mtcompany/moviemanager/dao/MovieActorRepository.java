package com.mtcompany.moviemanager.dao;

import com.mtcompany.moviemanager.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor,Long> {

    List<MovieActor> findByActor_Id(Long id);

    boolean existsByActor_IdAndMovie_Id(Long actorId, Long movieId);

    MovieActor findByActor_IdAndMovie_Id(Long actorId, Long movieId);
}
