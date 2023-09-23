package com.crejk.release.application;

import com.crejk.release.domain.Movie;
import com.crejk.release.domain.MovieId;
import com.crejk.release.domain.MovieReleased;
import com.crejk.release.domain.MovieRepository;
import com.crejk.release.shared.event.domain.DomainEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class MovieService {

    private final MovieRepository movieRepository;
    private final DomainEventPublisher eventPublisher;

    public MovieService(MovieRepository movieRepository, DomainEventPublisher eventPublisher) {
        this.movieRepository = movieRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public MovieId release(String movieTitle) {
        Movie movie = movieRepository.save(new Movie(movieTitle));
        eventPublisher.publish(new MovieReleased(movie.getId(), movie.getTitle()));
        return movie.getId();
    }
}
