package com.github.rw90.exjobb.MapApp.model;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

public class TraceMap {

    private final int MAX_ENTRIES = 2;
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
        timeUUIDMap.remove(oldTimeStamp);
        timeUUIDMap.put(timestamp, traceId);
    }

    public Optional<Trace> getOldestTrace() {
        UUID traceId;
        if(timeUUIDMap.keySet().size() > MAX_ENTRIES) {
            traceId = timeUUIDMap.pollFirstEntry().getValue();
            Trace lastTrace = uuidTraceMap.get(traceId);
            uuidTraceMap.remove(traceId);
            return Optional.of(lastTrace);
        }
        return Optional.empty();
    }

    public void addNewTrace(UUID traceId, String serviceName, LocalDateTime timestamp) {
        Trace traceToAdd = new Trace(traceId, timestamp, serviceName);
        timeUUIDMap.put(timestamp, traceId);
        uuidTraceMap.put(traceId, traceToAdd);
    }
}
