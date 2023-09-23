package com.crejk.release;

import com.crejk.release.api.ReleaseMovieRequest;
import com.crejk.release.api.ReleaseMovieResponse;
import com.crejk.release.domain.MovieReleased;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@Import({TestChannelBinderConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReleaseTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OutputDestination output;

    @Test
    void shouldReleaseMovie() {
        // given
        var movieTitle = "Fast & Furious";

        // when
        ReleaseMovieResponse response = restTemplate.postForObject("/release", new ReleaseMovieRequest(movieTitle), ReleaseMovieResponse.class);

        // then
        assertThat(response).isNotNull();

        // when
        MovieReleased event = receiveEvent();

        // then
        assertThat(event)
                .isNotNull()
                .hasFieldOrPropertyWithValue("movieTitle", movieTitle);
    }

    private MovieReleased receiveEvent() {
        Message<byte[]> eventMessage = output.receive(TimeUnit.SECONDS.toMillis(5), "events");
        try {
            return objectMapper.readValue(eventMessage.getPayload(), MovieReleased.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
