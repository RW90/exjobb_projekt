package com.github.rw90.exjobb.MapApp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to revert the ordering of lines in text files.
 */
public class FileReverser {

    private FileReverser() {}

    /**
     * Takes a text file and creates a new file where the lines of the initial text file is reverted.
     * @param fileToRevert Path to the text file to reverse
     * @param newFile Path to where the reversed lines should be written
     * @param linesToSkip Number of lines, from the beginning, to skip
     * @return Path to where the reversed lines are written
     * @throws IOException
     */
    public static Path reverseLinesInFile(Path fileToRevert, Path newFile, int linesToSkip) throws IOException {
        List<String> linesInFile = Files
                .lines(fileToRevert)
                .skip(linesToSkip)
                .collect(Collectors.toList());

        Collections.reverse(linesInFile);

        return Files.write(newFile, linesInFile);
    }
}
