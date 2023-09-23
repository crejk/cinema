package com.crejk.release.shared.event.infrastructure;

import com.crejk.release.shared.event.domain.DomainEvent;
import com.crejk.release.shared.event.domain.DomainEventPublisher;
import com.crejk.release.shared.event.domain.StoredDomainEvent;
import com.crejk.release.shared.event.domain.StoredDomainEventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

class StoreAndForwardDomainEventPublisher implements DomainEventPublisher {

    private final StoredDomainEventRepository storedEventRepository;
    private final DomainEventPublisher eventPublisher;
    private final EventSerializer eventSerializer;

    public StoreAndForwardDomainEventPublisher(StoredDomainEventRepository storedEventRepository, DomainEventPublisher eventPublisher, EventSerializer eventSerializer) {
        this.storedEventRepository = storedEventRepository;
        this.eventPublisher = eventPublisher;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public void publish(DomainEvent event) {
        String serialized = eventSerializer.serialize(event);
        storedEventRepository.save(new StoredDomainEvent(serialized));
    }

    @Transactional
    @Scheduled(fixedRate = 5000L)
    public void publishEventsPeriodically() {
        storedEventRepository.findAllBySentOrderByCreatedAtDesc(false).forEach(this::publishEvent);
    }

    private void publishEvent(StoredDomainEvent event) {
        DomainEvent domainEvent = eventSerializer.deserialize(event.getContent());
        eventPublisher.publish(domainEvent);
        event.markAsSent();
    }
}
