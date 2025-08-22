package com.sandeep.grpc.server;

import com.sandeep.grpc.proto.GreetingServiceGrpc;
import com.sandeep.grpc.proto.HelloRequest;
import com.sandeep.grpc.proto.HelloResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Received greeting request for: {}", request.getName());
        
        String message = "Hello, " + request.getName() + "! Welcome to gRPC with Spring Boot.";
        
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .setTimestamp(System.currentTimeMillis())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        
        logger.info("Sent greeting response: {}", response.getMessage());
    }

    @Override
    public void sayHelloServerStreaming(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Received server streaming request for: {}", request.getName());
        
        // Send multiple responses
        for (int i = 1; i <= 3; i++) {
            String message = "Hello " + request.getName() + " - Message " + i + " of 3";
            
            HelloResponse response = HelloResponse.newBuilder()
                    .setMessage(message)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            
            responseObserver.onNext(response);
            logger.info("Sent streaming response {}: {}", i, response.getMessage());
            
            // Add a small delay to demonstrate streaming
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        responseObserver.onCompleted();
        logger.info("Completed server streaming for: {}", request.getName());
    }
}