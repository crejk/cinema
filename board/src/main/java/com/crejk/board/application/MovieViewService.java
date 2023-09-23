package com.crejk.board.application;

import com.crejk.board.domain.MovieId;
import com.crejk.board.domain.MovieView;
import com.crejk.board.domain.MovieViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MovieViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieViewService.class);

    private final MovieViewRepository movieViewRepository;

    public MovieViewService(MovieViewRepository movieViewRepository) {
        this.movieViewRepository = movieViewRepository;
    }

    @Transactional
    public void save(MovieId movieId, String movieTitle) {
        movieViewRepository.findByMovieId(movieId.value()).ifPresentOrElse(
                movieView -> LOGGER.warn("Movie with id {} already exists.", movieId),
                () -> {
                    MovieView movieView = new MovieView(movieId, movieTitle);
                    movieViewRepository.save(movieView);
                });
    }

    public List<MovieView> get10RecentMovies() {
        PageRequest pageRequest = PageRequest.ofSize(10)
                .withSort(Sort.Direction.DESC, "releaseDate");
        return movieViewRepository.findAll(pageRequest);
    }
}
