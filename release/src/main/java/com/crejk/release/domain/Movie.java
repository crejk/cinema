package com.crejk.release.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Movie {

    @GeneratedValue
    @Id
    private Long movieId;
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    protected Movie() {
    }

    public MovieId getId() {
        return new MovieId(movieId);
    }

    public String getTitle() {
        return title;
    }
}
