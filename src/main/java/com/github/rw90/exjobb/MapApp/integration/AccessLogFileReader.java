package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.util.FileReverser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class has the ability to read a .csv-file of access logs with three value fields. It is assumed that
 * the first line of the file is a header line and that the first value field is a timestamp that should be omitted.
 * It is also assumed that the first second value field is a log message and that the third value field is a
 * service name. It is also assumed that the oldest log entries are at the bottom of the file.
 */
@Component
public class AccessLogFileReader implements LogFileReader<String[]> {

    private final int HEADER_LINE = 1;
    private final int MESSAGE_FIELD = 1;
    private final int SERVICE_NAME_FIELD = 3;

    private final Path pathToLogFile;

    /**
     * Creates an instance.
     * @param pathToAccessLogFile Path to the access log file that should be read
     */
    public AccessLogFileReader(@Qualifier("accessLogPath") Path pathToAccessLogFile) {
        this.pathToLogFile = pathToAccessLogFile;
    }

    /**
     * Reads the access log file and returns a Flux of String arrays representing each log entry.
     * First entry of an array is the access log entry message and the second entry is the service name.
     * The Flux is emitting the String arrays with the oldest entries first, given the assumptions taken by
     * this class.
     * @return Flux of log entries as String arrays.
     * @throws IOException
     */
    @Override
    public Flux<String[]> readAllLines() throws IOException {
        Path csvTempFile = Files.createTempFile("accesslogs", ".csv");
        Path reversedCsvTempFile = FileReverser.reverseLinesInFile(pathToLogFile, csvTempFile, HEADER_LINE);

        Stream<String[]> logEntries = csvLogFileToStream(reversedCsvTempFile);

        return Flux.fromStream(logEntries);
    }

    /**
     * Helper to read the log file and remove the first value field containing a timestamp.
     * @param pathToFile Path to the file that should be read. Should not contain header.
     * @return Stream of String arrays, where first entry is log message and second is service name.
     * @throws IOException
     */
    private Stream<String[]> csvLogFileToStream(Path pathToFile) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(pathToFile.toFile()));
        List<String[]> logEntries;

        try {
            logEntries = reader.readAll();
        } catch (CsvException e) {
            throw new IOException(e.getMessage());
        }

        return logEntries.stream()
                .map(entry -> Arrays.copyOfRange(entry, MESSAGE_FIELD, SERVICE_NAME_FIELD));
    }
}
