package com.github.rw90.exjobb.MapApp.integration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import reactor.core.publisher.Flux;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AccessLogFileReader {

    private Path pathToLogFile;

    public AccessLogFileReader(Path pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    // takes path to unmodified csv access log file and returns flux of string arrays containing values from each line in file
    public Flux<String[]> readAll() throws IOException, CsvException {
        Path logFilePreparedForReading = createReversedLogFile(pathToLogFile, Path.of("src/main/resources/tmp.csv"));
        List<String[]> logEntries = logFileToList(logFilePreparedForReading);
        return Flux.fromStream(logEntries.stream().map(entry -> Arrays.copyOfRange(entry, 1, 3)));
    }

    // read file but skip headers, reverse and write to new file. return path to new file
    private Path createReversedLogFile(Path from, Path to) throws IOException {
        List<String> linesFromLogFile = Files
                .lines(from)
                .skip(1)
                .collect(Collectors.toList());

        Collections.reverse(linesFromLogFile);

        return Files.write(to, linesFromLogFile);
    }

    // read csv-file top to bottom and return list of string arrays containg the comma separated values
    private List<String[]> logFileToList(Path pathToFile) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(pathToFile.toFile()));
        List<String[]> logEntries = reader.readAll();
        return logEntries;
    }
}
