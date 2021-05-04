package com.github.rw90.exjobb.MapApp.util;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class FileReverserImplTest {

    private final String ROW1 = "row 1";
    private final String ROW2 = "row 2";
    private final String ROW3 = "row 3";
    private final String ROW4 = "row 4";
    private final String ROW5 = "row 5";
    private Path fileToReverse;
    private Path reversedFile;
    private FileReverserImpl fileReverser;

    @BeforeEach
    void setUp() {
        fileReverser = new FileReverserImpl();
        List<String> lines = Arrays.asList(ROW1, ROW2, ROW3, ROW4, ROW5);

        try {
            fileToReverse = Files.createTempFile("nonreversed", ".txt");
            reversedFile = Files.write(fileToReverse, lines);
        } catch (IOException e) {
            System.err.println("Could not create test file: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.delete(reversedFile);
        } catch (IOException e) {
            System.err.println("Could not delete test file: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("should skip first line when told to")
    void reverseLinesInFileSkip1() throws IOException {
        fileReverser.reverseLinesInFile(fileToReverse, reversedFile, 1);
        Stream<String> lines = Files.lines(reversedFile);
        String expected = ROW2;
        String actual = lines.reduce((first, second) -> second).get(); // gets the last line
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("should not skip any line when not told to")
    void reverseLinesInFileSkip0() throws IOException {
        fileReverser.reverseLinesInFile(fileToReverse, reversedFile, 0);
        Stream<String> lines = Files.lines(reversedFile);
        String expected = ROW1;
        String actual = lines.reduce((first, second) -> second).get(); // gets the last line
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("should not return anything when skipping all lines")
    void reverseLinesInFileSkip6() throws IOException {
        fileReverser.reverseLinesInFile(fileToReverse, reversedFile, 6);
        Stream<String> lines = Files.lines(reversedFile);
        Optional<String> shouldBeEmpty = lines.findAny();
        Assertions.assertTrue(shouldBeEmpty.isEmpty());
    }
}