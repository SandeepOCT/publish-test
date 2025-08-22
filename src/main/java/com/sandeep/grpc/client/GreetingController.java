package com.sandeep.grpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingClient greetingClient;

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name) {
        return greetingClient.sendGreeting(name);
    }

    @GetMapping("/greet-stream")
    public String greetStream(@RequestParam(defaultValue = "World") String name) {
        greetingClient.sendStreamingGreeting(name);
        return "Check logs for streaming responses for: " + name;
    }
}