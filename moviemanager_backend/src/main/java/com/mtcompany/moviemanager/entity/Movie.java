package com.mtcompany.moviemanager.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtcompany.moviemanager.Views;
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
    @JsonView(Views.Detailed.class)
    private Long id;


    @Column(name = "title")
    @JsonView(Views.Detailed.class)
    private String title;

    @Column(name = "production_year")
    @JsonView(Views.Detailed.class)
    private int productionYear;

    @Column(name = "description")
    @JsonView(Views.Detailed.class)
    private String description;

    @Column(name = "img_path")
    @JsonView(Views.Detailed.class)
    private String imgPath;


    @Column(name = "director_id")
    @JsonView(Views.Detailed.class)
    private Long directorId;

    @Column(name = "category_id")
    private Long categoryId;

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
