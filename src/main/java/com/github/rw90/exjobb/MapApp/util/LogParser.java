package com.github.rw90.exjobb.MapApp.util;

import reactor.core.publisher.Flux;

public class LogParser {

    //TODO: Fixa denna fullösning kanske?
    public static Flux<String> parseSystemNamesFromLogs(Flux<String> log) {
        return log
                .map(line -> line.split("appName=")[1])
                .map(line -> line.substring(0, line.indexOf(",")))
                .distinct();
    }

}
//order, appVersion=1.0.422, scmRevision=78bc326]

//"2021-04-14 04:09:38,567 INFO  [main] [org.springframework.boot.actuate.audit.listener.AuditListener] [NONE] - AuditEvent [timestamp=1618366178566, type=ContextRefreshed, appName=order, appVersion=1.0.422, scmRevision=78bc326]"

//"2021-04-14 02:09:38.567","2021-04-14 04:09:38,567 INFO  [main] [org.springframework.boot.actuate.audit.listener.AuditListener] [NONE] - AuditEvent [timestamp=1618366178566, type=ContextRefreshed, appName=order, appVersion=1.0.422, scmRevision=78bc326]"