package com.github.rw90.exjobb.MapApp.util;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import reactor.core.publisher.Flux;

public class LogParser {

    //TODO: Fixa denna fullösning kanske?
    public static Flux<String> parseSystemNamesFromLogs(Flux<String> log) {
        return log
                .map(line -> line.split("appName=")[1])
                .map(line -> line.substring(0, line.indexOf(",")))
                .distinct();
    }

    public static Flux<AccessLogLine> parseAccessLogLine(Flux<String> log) {

        return null;
    }

}
