package com.ice.songsapp.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;



@RestController
@AllArgsConstructor
@Slf4j
public class SongsAppController {
    @GetMapping("/hello-world")
    public Flux<String> getAvailablePromotions() {
        return Flux.just("Hello World!");
    }
}
