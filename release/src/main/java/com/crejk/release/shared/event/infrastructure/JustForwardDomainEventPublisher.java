package com.crejk.release.shared.event.infrastructure;

import com.crejk.release.shared.event.domain.DomainEvent;
import com.crejk.release.shared.event.domain.DomainEventPublisher;
import org.springframework.cloud.stream.function.StreamBridge;

class JustForwardDomainEventPublisher implements DomainEventPublisher {

    private final StreamBridge streamBridge;

    JustForwardDomainEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void publish(DomainEvent event) {
        streamBridge.send("events", event);
    }
}
