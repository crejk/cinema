package com.crejk.release.shared.event.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface StoredDomainEventRepository extends Repository<StoredDomainEvent, String> {

    StoredDomainEvent save(StoredDomainEvent event);

    List<StoredDomainEvent> findAllBySentOrderByCreatedAtDesc(boolean sent);
}
