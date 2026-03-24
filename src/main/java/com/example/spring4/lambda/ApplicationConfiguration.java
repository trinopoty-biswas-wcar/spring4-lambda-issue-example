package com.example.spring4.lambda;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationConfiguration {

    private String message;
}
