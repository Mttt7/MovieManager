package com.mtcompany.moviemanager.dao;


import com.mtcompany.moviemanager.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {
}
