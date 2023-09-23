package com.crejk.board;

import com.crejk.board.application.MovieReleasedHandler;
import com.crejk.board.application.MovieViewService;
import com.crejk.board.domain.MovieViewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoardConfiguration {

    @Bean
    MovieViewService movieService(MovieViewRepository movieViewRepository) {
        return new MovieViewService(movieViewRepository);
    }

    @Bean
    MovieReleasedHandler movieReleasedHandler(MovieViewService movieViewService) {
        return new MovieReleasedHandler(movieViewService);
    }
}
