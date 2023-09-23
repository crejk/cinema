package com.crejk.release.api;

import com.crejk.release.application.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MovieController {

    private final MovieService movieService;

    MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/release")
    ReleaseMovieResponse release(@RequestBody ReleaseMovieRequest request) {
        return new ReleaseMovieResponse(movieService.release(request.title()).value());
    }
}
