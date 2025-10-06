package com.sandeep.grpc.client;

import com.sandeep.grpc.proto.GreetingServiceGrpc;
import com.sandeep.grpc.proto.HelloRequest;
import com.sandeep.grpc.proto.HelloResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class GreetingClient {

    private static final Logger logger = LoggerFactory.getLogger(GreetingClient.class);

    @GrpcClient("local-grpc-server")
    private GreetingServiceGrpc.GreetingServiceBlockingStub blockingStub;

    @GrpcClient("local-grpc-server")
    private GreetingServiceGrpc.GreetingServiceStub asyncStub;

    public String sendGreeting(String name) {
        logger.info("Sending greeting request for: {}", name);
        
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        
        HelloResponse response = blockingStub.sayHello(request);
        
        logger.info("Received greeting response: {}", response.getMessage());
        return response.getMessage();
    }

    public void sendStreamingGreeting(String name) {
        logger.info("Sending server streaming request for: {}", name);
        
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        
        CountDownLatch latch = new CountDownLatch(1);
        
        asyncStub.sayHelloServerStreaming(request, new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse response) {
                logger.info("Received streaming response: {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("Error in streaming call", throwable);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Server streaming completed");
                latch.countDown();
            }
        });
        
        try {
            // Wait for the streaming to complete (max 30 seconds)
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted while waiting for streaming response", e);
        }
    }
}