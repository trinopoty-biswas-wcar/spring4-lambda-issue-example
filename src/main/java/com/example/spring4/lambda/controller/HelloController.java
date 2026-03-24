package com.example.spring4.lambda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    public record HelloResponse(String message) {
    }

    private final String message;

    public HelloController(@Value( "${application.message}" ) String message) {
        log.info( "Message from properties: {}", message );
        this.message = message;
    }

    @GetMapping
    public HelloResponse hello() {
        return new HelloResponse( message );
    }
}
