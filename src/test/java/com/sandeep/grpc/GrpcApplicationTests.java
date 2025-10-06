package com.sandeep.grpc;

import com.sandeep.grpc.client.GreetingClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GrpcApplicationTests {

    @Autowired
    private GreetingClient greetingClient;

    @Test
    void contextLoads() {
        assertNotNull(greetingClient);
    }

    @Test
    void testGreetingService() {
        String response = greetingClient.sendGreeting("Test User");
        assertNotNull(response);
        assertTrue(response.contains("Hello, Test User"));
        assertTrue(response.contains("Welcome to gRPC with Spring Boot"));
    }
}