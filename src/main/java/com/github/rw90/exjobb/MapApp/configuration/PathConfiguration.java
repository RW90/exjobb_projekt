package com.github.rw90.exjobb.MapApp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PathConfiguration {

    @Bean("accessLogPath")
    public Path getAccessLogFilePath(@Value("${logs.access}") String pathToAccessLogFile) {
        return Paths.get(pathToAccessLogFile);
    }
}
