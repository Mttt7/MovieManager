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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public Actor() {
    }

    public Actor( String firstName, String lastName, LocalDate birthDate, String description, String imgPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.description = description;
        this.imgPath = imgPath;
    }

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
}
