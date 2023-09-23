package com.crejk.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
public class MovieView {

    @GeneratedValue
    @Id
    private Long id;
    private Long movieId;
    private String title;
    @CreatedDate
    private Instant releaseDate;

    public MovieView(MovieId movieId, String title) {
        this.movieId = movieId.value();
        this.title = title;
    }

    protected MovieView() {
    }

    public MovieId getMovieId() {
        return new MovieId(movieId);
    }

    public String getTitle() {
        return title;
    }
}
