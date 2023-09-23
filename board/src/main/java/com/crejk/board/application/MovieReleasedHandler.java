package com.crejk.board.application;

import com.crejk.board.domain.MovieReleased;

import java.util.function.Consumer;

public class MovieReleasedHandler implements Consumer<MovieReleased> {

    private final MovieViewService movieViewService;

    public MovieReleasedHandler(MovieViewService movieViewService) {
        this.movieViewService = movieViewService;
    }

    @Override
    public void accept(MovieReleased movieReleased) {
        movieViewService.save(movieReleased.movieId(), movieReleased.movieTitle());
    }
}
