package com.example.spring4.lambda.function;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.example.spring4.lambda.BootApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequiredArgsConstructor
@Slf4j
public class SpringLambda implements RequestStreamHandler {

    private final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    public SpringLambda() {
        this.handler = BootApplication.initializeSpringLambdaContainer();
    }

    // For debugging JAR boot issue
    public static void main(String[] args) throws Exception {
        var lambda = new SpringLambda();
        Thread.sleep( 5000 );
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        this.handler.proxyStream( input, output, context );
    }
}
