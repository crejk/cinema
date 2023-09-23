package com.crejk.board;

import com.crejk.board.api.MoviesResponse;
import com.crejk.board.domain.MovieId;
import com.crejk.board.domain.MovieReleased;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@Import({TestChannelBinderConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardTest {

    @Autowired
    private InputDestination input;

    @Autowired
    private TestRestTemplate restTemplate;

    private final AtomicLong idGenerator = new AtomicLong();

    @AfterEach
    void tearDown() {
        // TODO clear database
        idGenerator.set(0);
    }

    @Test
    void shouldDisplayReleasedMovie() {
        // given
        String movieTitle = "Fast & Furious";
        sendEventAboutMovieRelease(movieTitle);

        // when
        MoviesResponse response = restTemplate.getForObject("/movies", MoviesResponse.class);

        // then
        assertThat(response.movies())
                .hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("movieTitle", movieTitle);
    }

    @Test
    void shouldDisplayOnly10RecentReleasedMovies() {
        // given
        var moviesCount = 11;
        var baseMovieTitle = "Fast & Furious";
        for (int i = 0; i < moviesCount; i++) {
            String movieTitle = baseMovieTitle + i;
            sendEventAboutMovieRelease(movieTitle);
        }

        // when
        MoviesResponse response = restTemplate.getForObject("/movies", MoviesResponse.class);

        // then
        assertThat(response.movies())
                .hasSize(10)
                .allSatisfy(movieResponse -> assertThat(movieResponse.movieTitle()).isNotEqualToIgnoringCase(baseMovieTitle + "-0"));
    }

    private void sendEventAboutMovieRelease(String movieTitle) {
        MovieReleased event = new MovieReleased(new MovieId(idGenerator.getAndIncrement()),movieTitle);
        input.send(new GenericMessage<>(event));
    }
}
