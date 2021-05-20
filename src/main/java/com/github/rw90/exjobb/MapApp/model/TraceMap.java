package com.github.rw90.exjobb.MapApp.model;

import java.time.LocalDateTime;
import java.util.*;

public class TraceMap {

    private final int MAX_ENTRIES = 5;
    private final TreeMap<LocalDateTime, UUID> timeUUIDMap;
    private final Map<UUID, Trace> uuidTraceMap;

    public TraceMap() {
        timeUUIDMap = new TreeMap<LocalDateTime, UUID>();
        uuidTraceMap = new HashMap<>();
    }

    public boolean traceExists(UUID traceId) {
        return uuidTraceMap.containsKey(traceId);
    }

    public void insertService(UUID traceId, String serviceName, LocalDateTime timestamp) {
        Trace trace = uuidTraceMap.get(traceId);
        LocalDateTime oldTimeStamp = trace.getTimeOfLastRequest();
        trace.addService(serviceName, timestamp);
        trace.setTimeOfLastRequest(timestamp);
        timeUUIDMap.remove(oldTimeStamp);
        timeUUIDMap.put(timestamp, traceId);
    }

    public Trace getOldestTrace() {
        UUID traceId;
        if(timeUUIDMap.size() > MAX_ENTRIES) {
            traceId = timeUUIDMap.pollFirstEntry().getValue();
            Trace lastTrace = uuidTraceMap.get(traceId);
            uuidTraceMap.remove(traceId);
            return lastTrace;

        } else {
            traceId = timeUUIDMap.firstEntry().getValue();
            return uuidTraceMap.get(traceId);
        }
    }

    public void addNewTrace(UUID traceId, String serviceName, LocalDateTime timestamp) {
        Trace traceToAdd = new Trace(traceId, timestamp, serviceName);
        timeUUIDMap.put(timestamp, traceId);
        uuidTraceMap.put(traceId, traceToAdd);
    }
}

/**
traceMap.insertService(wrapper.logLine.getTraceId(), wrapper.logLine.getServiceName(), wrapper.logLine.getTimestamp());
        } else {
        Trace traceToAdd = traceMap.getOldestTrace();
        traceMap.addNewTrace(wrapper.logLine.getTraceId(), wrapper.logLine.getServiceName(), wrapper.logLine.getTimestamp());
 */