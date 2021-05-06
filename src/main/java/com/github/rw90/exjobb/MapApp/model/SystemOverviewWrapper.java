package com.github.rw90.exjobb.MapApp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class SystemOverviewWrapper implements Comparable<SystemOverviewWrapper> {

    private LocalDateTime timeStamp;
    private SystemOverview systemOverview;

    public SystemOverviewWrapper(SystemOverview systemOverview) {
        this.systemOverview = systemOverview;
        this.timeStamp = LocalDateTime.now();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public SystemOverview getSystemOverview() {
        return systemOverview;
    }

    @Override
    public int compareTo(SystemOverviewWrapper o) {
        return timeStamp.compareTo(o.getTimeStamp());
    }
}
