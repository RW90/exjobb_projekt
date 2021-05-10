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
    private String latestChange;

    public SystemOverviewWrapper(SystemOverview systemOverview, String latestChange) {
        this.systemOverview = systemOverview;
        this.timeStamp = LocalDateTime.now();
        this.id = UUID.randomUUID();
        this.latestChange = latestChange;
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

    public String getLatestChange() {
        return latestChange;
    }

    @Override
    public int compareTo(SystemOverviewWrapper o) {
        return timeStamp.compareTo(o.getTimeStamp());
    }
}
