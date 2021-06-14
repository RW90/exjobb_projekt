package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccessLogParser implements LogParser<AccessLogLine> {

    private final int MESSAGE_FIELD = 0;
    private final int SERVICE_NAME_FIELD = 1;
    private final int HTTP_METHOD_FIELD = 0;
    private final int REQUEST_PATH = 1;
    private final String UUID_PATTERN = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";
    private final LogFileReader<String[]> reader;

    public AccessLogParser(@Qualifier("accessLogFileReader") LogFileReader reader) {
        this.reader = reader;
    }

    @Override
    public Flux<AccessLogLine> parseLines() throws IOException {
        return reader
                .readAllLines()
                .map(this::logEntryToAccessLogLine);
    }

    private AccessLogLine logEntryToAccessLogLine(String[] logEntry) {
        HttpMethod method = extractMethod(logEntry);
        String path = extractPath(logEntry);
        String serviceName = extractServiceName(logEntry);
        UUID corrId = extractCorrId(logEntry);
        LocalDateTime timestamp = extractTimestamp(logEntry);
        return new AccessLogLine(method, path, serviceName, corrId, timestamp);
    }

    private HttpMethod extractMethod(String[] logEntry) {
        String method = extractRequestLineParts(logEntry)[HTTP_METHOD_FIELD];
        return HttpMethod.valueOf(method);
    }

    private String extractPath(String[] logEntry) {
        String pathWithParams = extractRequestLineParts(logEntry)[REQUEST_PATH];
        return StringUtils.substringBefore(pathWithParams, "?");
    }

    private String extractServiceName(String[] logEntry) {
        return logEntry[SERVICE_NAME_FIELD];
    }

    private UUID extractCorrId(String[] logEntry) {
        Pattern pattern = Pattern.compile(UUID_PATTERN);
        Matcher matcher = pattern.matcher(logEntry[MESSAGE_FIELD]);

        if (matcher.find()) {
            return UUID.fromString(matcher.group());
        } else {
            return null;
        }
    }

    private String[] extractRequestLineParts(String[] logEntry) {
        String logMessage = logEntry[MESSAGE_FIELD];
        String requestLine = StringUtils.substringBetween(logMessage, "\"");
        return requestLine.split(" ");
    }

    private LocalDateTime extractTimestamp(String[] logEntry) {


        String regex = "(\\d{4}-\\d{2}\\-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry[0]);
        String timeAsString = null;
        if (matcher.find()) {
            timeAsString = matcher.group(1);
        }

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        if(timeAsString == null) { // TODO: Ta bort denna skit
            return LocalDateTime.of(1900, 10,1,1,1);
        }
        return LocalDateTime.parse(timeAsString, timeFormat);

    }

}