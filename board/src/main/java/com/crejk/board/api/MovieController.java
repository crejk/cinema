package com.crejk.board.api;

import com.crejk.board.application.MovieViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MovieController {

    private final MovieViewService movieViewService;

    MovieController(MovieViewService movieViewService) {
        this.movieViewService = movieViewService;
    }

    @GetMapping("/movies")
    MoviesResponse recentMovies() {
        return new MoviesResponse(movieViewService.get10RecentMovies()
                .stream()
                .map(it -> new MovieResponse(it.getTitle()))
                .toList()
        );
    }
}
