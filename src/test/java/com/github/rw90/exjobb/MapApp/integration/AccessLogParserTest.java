package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class AccessLogParserTest {

    private AccessLogParser parser;
    private LogFileReader reader;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        List<String> lines = Arrays.asList(
                "\"@timestamp\",message,\"kubernetes.labels.app\"",
                "\"2021-04-14 17:20:58.061\", \"2021-04-14 19:20:58,061 ipaddr - - \"\"GET /adress?billigid=34&from=2018-11 HTTP/1.1\"\" 200 12 24 [0aecdb60-0338-49b4-ac36-f5fd871ef7c2] \"\"Apache-HttpClient/4.5.8 (Java/11.0.10)\"\"\", \"adress\"",
                "\"2021-04-14 17:15:35.000\",\"ipaddr - - [14/Apr/2021:19:15:35 +0200] \"\"POST /startpoint2 HTTP/1.1\"\" 200 167 \"\"-\"\" \"\"Apache-HttpClient/4.5.10 (Java/1.8.0_242)\"\" \"\"ipaddr\"\"\",\"adress-gw\"",
                "\"2021-04-14 17:15:35.427\",\"2021-04-14 19:15:35,427 ipaddr - - \"\"POST /calculate HTTP/1.1\"\" 200 215 1 [525891db-d583-4dfa-87ca-9500f8da0192] \"\"Java-SDK\"\"\",\"calculate\""
        );

        tempFile = Files.createTempFile("testLogs", ".csv");
        Files.write(tempFile, lines);
        reader = new AccessLogFileReader(tempFile);
        parser = new AccessLogParser(reader);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(tempFile);
    }

    @Test
    @DisplayName("should return all lines except headers")
    void allLines() throws IOException {
        Flux<AccessLogLine> lines = parser.parseLines();
        StepVerifier
                .create(lines)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    @DisplayName("should parse line")
    void correctParsing() throws IOException {
        Flux<AccessLogLine> lines = parser.parseLines();
        AccessLogLine expected = new AccessLogLine(HttpMethod.POST, "/calculate", "calculate", UUID.fromString("525891db-d583-4dfa-87ca-9500f8da0192"));
        StepVerifier
                .create(lines)
                .expectNext(expected)
                .thenCancel()
                .verify();
    }

    @Test
    @DisplayName("should not include request params when parsing")
    void correctParsing2() throws IOException {
        Flux<AccessLogLine> lines = parser.parseLines();
        AccessLogLine expected = new AccessLogLine(HttpMethod.GET, "/adress", "adress", UUID.fromString("0aecdb60-0338-49b4-ac36-f5fd871ef7c2"));
        StepVerifier
                .create(lines)
                .expectNextCount(2)
                .expectNext(expected)
                .thenCancel()
                .verify();
    }

    @Test
    @DisplayName("should parse both types of formatted log messages")
    void correctParsing3() throws IOException {
        Flux<AccessLogLine> lines = parser.parseLines();
        AccessLogLine expected = new AccessLogLine(HttpMethod.POST, "/startpoint2", "adress-gw", null);
        StepVerifier
                .create(lines)
                .expectNextCount(1)
                .expectNext(expected)
                .thenCancel()
                .verify();
    }
}