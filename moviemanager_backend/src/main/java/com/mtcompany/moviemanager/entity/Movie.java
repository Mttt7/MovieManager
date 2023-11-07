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


//chuj
//    @ManyToOne(fetch = FetchType.EAGER,
//            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinColumn(name = "director_id")
//    private Director director;

//    @ManyToMany(fetch = FetchType.LAZY,
//    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinTable(
//            name = "movie_actor",
//            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id")
//    )
//    private List<Actor> actors;

    @Column(name = "director_id")
    private Long directorId;

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public Movie() {
    }

    public Movie(String title, int productionYear, String description, String imgPath) {
        this.title = title;
        this.productionYear = productionYear;
        this.description = description;
        this.imgPath = imgPath;
    }


//    public List<Actor> getActors() {
//        return actors;
//    }

//    public void setActors(List<Actor> actors) {
//        this.actors = actors;
//    }
    //    public Director getDirector() {
//        return director;
//    }

//    public void setDirector(Director director) {
//        this.director = director;
//    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productionYear=" + productionYear +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +'}';
    }

//    public void addActor(Actor theActor){
//        if(actors == null){
//            actors = new ArrayList<>();
//        }
//
//        actors.add(theActor);
//
//    }

}
