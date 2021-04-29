package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.UUID;

public class AccessLogParser {

    private static final int MESSAGE_FIELD = 0;
    private static final int SERVICE_NAME_FIELD = 1;
    private static final int HTTP_METHOD_FIELD = 0;

    public static Flux<AccessLogLine> accessLogLineFlux(AccessLogFileReader reader) throws IOException, CsvException {
        return reader
                .readAll()
                .map(logEntry -> logEntryToAccessLogLine(logEntry));
    }

    private static AccessLogLine logEntryToAccessLogLine(String[] logEntry) {
        String serviceName = logEntry[SERVICE_NAME_FIELD];
        String logMessage = logEntry[MESSAGE_FIELD];
        String requestLine = StringUtils.substringBetween(logMessage, "\"");
        String[] requestLineParts = requestLine.split(" ");
        HttpMethod method = HttpMethod.valueOf(requestLineParts[HTTP_METHOD_FIELD]);
        int uuidStartIndex = StringUtils.lastIndexOf(logMessage, '[') + 1;
        int uuidEndIndex = StringUtils.lastIndexOf(logMessage,']');
        String possibleTraceId = logMessage.substring(uuidStartIndex, uuidEndIndex);
        UUID uuid = uuidOrNull(possibleTraceId);
        return new AccessLogLine(method, requestLineParts[1], serviceName, uuid);
    }

    private static UUID uuidOrNull(String possibleTraceId) {
        return possibleTraceId.length() == 36 ? UUID.fromString(possibleTraceId) : null;
    }
}