package com.mtcompany.moviemanager.dao;

import com.mtcompany.moviemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
