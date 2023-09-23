package com.crejk.release;

import com.crejk.release.application.MovieService;
import com.crejk.release.domain.MovieRepository;
import com.crejk.release.shared.event.domain.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReleaseConfiguration {

    @Bean
    MovieService movieService(MovieRepository movieRepository, DomainEventPublisher eventPublisher) {
        return new MovieService(movieRepository, eventPublisher);
    }
}
