package com.github.rw90.exjobb.MapApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class SystemOverviewWrapper implements Comparable<SystemOverviewWrapper> {

    @Id
    private UUID id;
    private LocalDateTime timeStamp;
    private SystemOverview systemOverview;

    public SystemOverviewWrapper(SystemOverview systemOverview) {
        this.systemOverview = systemOverview;
        this.timeStamp = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public SystemOverview getSystemOverview() {
        return systemOverview;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int compareTo(SystemOverviewWrapper o) {
        return timeStamp.compareTo(o.getTimeStamp());
    }
}
