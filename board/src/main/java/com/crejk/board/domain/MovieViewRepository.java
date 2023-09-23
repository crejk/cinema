package com.crejk.board.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface MovieViewRepository extends Repository<MovieView, Long> {

    Optional<MovieView> findByMovieId(long movieId);

    MovieView save(MovieView movieView);

    List<MovieView> findAll(Pageable pageable);
}
