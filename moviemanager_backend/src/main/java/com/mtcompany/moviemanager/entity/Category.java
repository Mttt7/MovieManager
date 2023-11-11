package com.mtcompany.moviemanager.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mtcompany.moviemanager.Views;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Summary.class)
    private Long id;

    @Column(name = "name")
    @JsonView(Views.Summary.class)
    private String name;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    @JoinColumn(name = "category_id")
    @JsonView(Views.Detailed.class)
    private List<Movie> movies;
}
