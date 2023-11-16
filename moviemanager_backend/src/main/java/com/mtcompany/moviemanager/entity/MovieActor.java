package com.mtcompany.moviemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie_actor")
@Getter
@Setter
public class MovieActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public MovieActor() {
    }

    @Override
    public String toString() {
        return "MovieActor{" +
                "id=" + id +
                ", actor=" + actor +
                ", movie=" + movie +
                '}';
    }

    public MovieActor(Actor actor, Movie movie) {
        this.actor = actor;
        this.movie = movie;


    }
}
