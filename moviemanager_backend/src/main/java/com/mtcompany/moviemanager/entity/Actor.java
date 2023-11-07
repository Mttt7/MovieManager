package com.mtcompany.moviemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {

    @Id
    @Column(name = "id")
    private int id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "description")
    private String description;

    @Column(name = "img_path")
    private String imgPath;

//    @ManyToMany(fetch = FetchType.LAZY,
//    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinTable(
//            name = "movie_actor",
//            joinColumns = @JoinColumn(name = "actor_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id")
//    )
//    private List<Movie> movies;

    public Actor() {
    }

    public Actor(int id, String firstName, String lastName, LocalDate birthDate, String description, String imgPath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.description = description;
        this.imgPath = imgPath;
    }




//    public List<Movie> getMovies() {
//        return movies;
//    }

//    public void setMovies(List<Movie> movies) {
//        this.movies = movies;
//    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

//    public void addMovie(Movie movie){
//        if(movies == null){
//            movies = new ArrayList<>();
//        }
//
//        movies.add(movie);
//        //movie.addActor(this);
//
//    }




}
