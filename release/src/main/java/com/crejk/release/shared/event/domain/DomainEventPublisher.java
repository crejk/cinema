package com.crejk.release.shared.event.domain;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
