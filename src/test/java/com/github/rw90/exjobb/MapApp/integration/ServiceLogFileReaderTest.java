package com.github.rw90.exjobb.MapApp.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class ServiceLogFileReaderTest {

    private ServiceLogFileReader reader;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        List<String> lines = Arrays.asList(
                "\"@timestamp\",\"log.original\"",
                "\"2021-04-14 02:09:38.567\",\"2021-04-14 04:09:38,567 INFO  [main] [org.springframework.boot.actuate.audit.listener.AuditListener] [NONE] - AuditEvent [timestamp=1618366178566, type=ContextRefreshed, appName=order, appVersion=1.0.422, scmRevision=78bc326]\"",
                "\"2021-04-14 02:09:34.197\",\"2021-04-14 04:09:34,197 INFO  [main] [org.springframework.boot.actuate.audit.listener.AuditListener] [NONE] - AuditEvent [timestamp=1618366174195, type=ContextRefreshed, appName=order, appVersion=1.0.422, scmRevision=78bc326]\"",
                "\"2021-04-14 02:09:07.497\",\"2021-04-14 04:09:07,497 INFO  [main] [org.springframework.boot.actuate.audit.listener.AuditListener] [NONE] - AuditEvent [timestamp=1618366147496, type=ContextRefreshed, appName=customer, appVersion=1.0.48, scmRevision=e1e335f]\""
        );

        tempFile = Files.createTempFile("testLogs", ".csv");
        Files.write(tempFile, lines);
        reader = new ServiceLogFileReader(tempFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(tempFile);
    }

    @Test
    @DisplayName("should ignore headers")
    void ignoreHeaders() throws IOException {
        Flux<String[]> lines = reader.readAllLines();
        StepVerifier
                .create(lines)
                .expectNextCount(3)
                .verifyComplete();
    }
}