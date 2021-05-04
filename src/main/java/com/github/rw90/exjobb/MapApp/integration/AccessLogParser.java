package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.UUID;

@Component
public class AccessLogParser implements LogParser<AccessLogLine> {

    private final int MESSAGE_FIELD = 0;
    private final int SERVICE_NAME_FIELD = 1;
    private final int HTTP_METHOD_FIELD = 0;

    @Override
    public Flux<AccessLogLine> logLinesFlux(CsvLogFileReader reader) throws IOException {
        return reader
                .readAllLines()
                .map(this::logEntryToAccessLogLine);
    }

    private AccessLogLine logEntryToAccessLogLine(String[] logEntry) {
        String serviceName = logEntry[SERVICE_NAME_FIELD];
        String logMessage = logEntry[MESSAGE_FIELD];
        String requestLine = StringUtils.substringBetween(logMessage, "\"");
        String[] requestLineParts = requestLine.split(" ");
        HttpMethod method = HttpMethod.valueOf(requestLineParts[HTTP_METHOD_FIELD]);
        int uuidStartIndex = StringUtils.lastIndexOf(logMessage, '[') + 1;
        int uuidEndIndex = StringUtils.lastIndexOf(logMessage,']');
        String possibleTraceId = logMessage.substring(uuidStartIndex, uuidEndIndex);
        UUID uuid = uuidOrNull(possibleTraceId);
        return new AccessLogLine(method, StringUtils.substringBefore(requestLineParts[1], "?"), serviceName, uuid);
    }

    private UUID uuidOrNull(String possibleTraceId) {
        return possibleTraceId.length() == 36 ? UUID.fromString(possibleTraceId) : null;
    }
}