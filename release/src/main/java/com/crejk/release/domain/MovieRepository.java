package com.crejk.release.domain;

import org.springframework.data.repository.Repository;

public interface MovieRepository extends Repository<Movie, Long> {

    Movie save(Movie movie);
}
