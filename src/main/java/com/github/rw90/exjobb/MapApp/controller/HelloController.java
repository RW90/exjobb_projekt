package com.github.rw90.exjobb.MapApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public Mono<String> getHelloMessage() {
        return Mono.just("Hello from Glada grabbarna!");
    }
}
