package com.example.spring4.lambda;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableConfigurationProperties(ApplicationConfiguration.class)
@EnableWebMvc
@Slf4j
@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run( BootApplication.class, args );
    }

    public static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> initializeSpringLambdaContainer() {
        try {
            final String springProfile = System.getenv( "SPRING_PROFILE" );
            final String[] springProfiles = StringUtils.hasText( springProfile ) ? springProfile.split( "," ) : new String[0];

            return SpringBootLambdaContainerHandler.getAwsProxyHandler( BootApplication.class, springProfiles );
        } catch (ContainerInitializationException ex) {
            log.error( "Unable to load Spring Boot Container Handler", ex );
            throw new RuntimeException( ex );
        }
    }
}
