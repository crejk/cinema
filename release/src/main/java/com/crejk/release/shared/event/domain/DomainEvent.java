package com.crejk.release.shared.event.domain;

import com.crejk.release.domain.MovieReleased;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovieReleased.class) }
)
public interface DomainEvent {

    @JsonIgnore
    String getType();
}
