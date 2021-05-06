package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.util.FileReverser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Flux;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ServiceLogFileReader implements LogFileReader {

    private final int HEADER_LINE = 1;
    private final int MESSAGE_FIELD = 1;

    private final Path pathToLogFile;

    public ServiceLogFileReader(@Qualifier("serviceLogPath") Path pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    @Override
    public Flux<String[]> readAllLines() throws IOException {
        Path csvTempFile = Files.createTempFile("servicelogs", ".csv");
        Path reversedCsvTempFile = FileReverser.reverseLinesInFile(pathToLogFile, csvTempFile, HEADER_LINE);

        Stream<String[]> logEntries = csvLogFileToStream(reversedCsvTempFile);

        return Flux.fromStream(logEntries);
    }

    private Stream<String[]> csvLogFileToStream(Path pathToFile) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(pathToFile.toFile()));
        List<String[]> logEntries;

        try {
            logEntries = reader.readAll();
        } catch (CsvException e) {
            throw new IOException(e.getMessage());
        }

        return logEntries.stream()
                .map(entry -> new String[] {entry[MESSAGE_FIELD]});
    }
}
