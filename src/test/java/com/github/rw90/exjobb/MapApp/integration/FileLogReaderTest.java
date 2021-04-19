package com.github.rw90.exjobb.MapApp.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileLogReaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readAllLines() {
        var reader = new FileLogReader();
        try {
            var lines = reader.readAllLines();
            lines.take(15).subscribe(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
            fail("IOException caught");
        }
    }
}