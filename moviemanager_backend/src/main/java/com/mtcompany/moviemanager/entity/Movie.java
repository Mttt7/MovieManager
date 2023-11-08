package com.mtcompany.moviemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "description")
    private String description;

    @Column(name = "img_path")
    private String imgPath;


    @Column(name = "director_id")
    private Long directorId;

    @Column(name = "category_id")
    private Long categoryId;


//    public Long getDirectorId() {
//        return directorId;
//    }
//
//    public void setDirectorId(Long directorId) {
//        this.directorId = directorId;
//    }

    public Movie() {
    }

    public Movie(String title, int productionYear, String description, String imgPath) {
        this.title = title;
        this.productionYear = productionYear;
        this.description = description;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productionYear=" + productionYear +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +'}';
    }

}
