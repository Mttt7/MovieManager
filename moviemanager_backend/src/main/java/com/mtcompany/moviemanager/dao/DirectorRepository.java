package com.mtcompany.moviemanager.dao;

import com.mtcompany.moviemanager.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {

}
