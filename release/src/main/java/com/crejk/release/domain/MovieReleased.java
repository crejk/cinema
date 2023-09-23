package com.crejk.release.domain;

import com.crejk.release.shared.event.domain.DomainEvent;

public record MovieReleased(MovieId movieId, String movieTitle) implements DomainEvent {

}
