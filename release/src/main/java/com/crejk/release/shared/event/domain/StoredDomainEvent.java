package com.crejk.release.shared.event.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Table(name = "events")
@Entity
public class StoredDomainEvent {

    @GeneratedValue
    @Id
    private Long eventId;
    private String content;
    private boolean sent;
    @CreatedDate
    private Instant createdAt;

    public StoredDomainEvent(String content) {
        this.content = content;
    }

    protected StoredDomainEvent() {
    }

    public String getContent() {
        return content;
    }

    public void markAsSent() {
        this.sent = true;
    }
}
