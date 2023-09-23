package com.crejk.release.shared.event.infrastructure;

import com.crejk.release.shared.event.domain.DomainEventPublisher;
import com.crejk.release.shared.event.domain.StoredDomainEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DomainEventPublisherConfiguration {

    @Bean
    DomainEventPublisher domainEventPublisher(StoredDomainEventRepository eventRepository, StreamBridge streamBridge) {
        return new StoreAndForwardDomainEventPublisher(eventRepository, new JustForwardDomainEventPublisher(streamBridge), eventSerializer());
    }

    private EventSerializer eventSerializer() {
        return new EventSerializer(new ObjectMapper());
    }
}
