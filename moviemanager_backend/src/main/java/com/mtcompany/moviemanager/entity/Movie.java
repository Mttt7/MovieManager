package com.mtcompany.moviemanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
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

//    @ManyToOne(fetch = FetchType.EAGER,
//            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinColumn(name = "director_id")
//    private Director director;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

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
}
