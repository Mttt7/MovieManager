package com.mtcompany.moviemanager.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtcompany.moviemanager.Views;
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
    @JsonView(Views.Summary.class)
    private Long id;

    @Column(name = "first_name")
    @JsonView(Views.Summary.class)
    private String firstName;

    @Column(name = "last_name")
    @JsonView(Views.Summary.class)
    private String lastName;

    @Column(name = "birth_date")
    @JsonView(Views.Summary.class)
    private LocalDate birthDate;

    @Column(name = "description")
    @JsonView(Views.Summary.class)
    private String description;

    @Column(name = "img_path")
    @JsonView(Views.Summary.class)
    private String imgPath;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            )
    @JoinColumn(name = "director_id")
    @JsonView(Views.Detailed.class)
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



//    public void deleteMovie(Movie movie){
//        movies.remove(movie);
//    }

    //-----------------------------------------------------
}
