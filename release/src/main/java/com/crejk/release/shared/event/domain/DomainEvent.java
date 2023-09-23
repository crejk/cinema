package com.crejk.release.shared.event.domain;

import com.crejk.release.domain.MovieReleased;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovieReleased.class, name = "movie_released") }
)
public interface DomainEvent {
}
