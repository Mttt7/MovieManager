package com.mtcompany.moviemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "director")
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            //,mappedBy = "director"
            )
    @JoinColumn(name = "director_id")
    private List<Movie> movies;


    public Director() {
    }

    public Director(String firstName, String lastName, LocalDate birthDate, String description, String imgPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.description = description;
        this.imgPath = imgPath;
    }



    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    //additional method

    public void addMovie(Movie movie){
        if(movies == null){
            movies = new ArrayList<>();
        }

        movies.add(movie);
        movie.setDirectorId((long) this.id);
    }

    public void deleteMovie(Movie movie){
        movies.remove(movie);
    }

}
