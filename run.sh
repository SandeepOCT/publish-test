#!/bin/bash

# gRPC Spring Boot Application Build and Run Script

echo "Building gRPC Spring Boot Application..."

# Clean and compile the project
mvn clean compile

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo "Build successful!"

# Run tests
echo "Running tests..."
mvn test

if [ $? -ne 0 ]; then
    echo "Tests failed!"
    exit 1
fi

echo "All tests passed!"

# Ask user if they want to run the application
read -p "Do you want to start the application? (y/n): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "Starting gRPC Spring Boot Application..."
    echo "- HTTP Server will be available at: http://localhost:8080"
    echo "- gRPC Server will be available at: localhost:9090"
    echo ""
    echo "Try these endpoints:"
    echo "  curl 'http://localhost:8080/greet?name=YourName'"
    echo "  curl 'http://localhost:8080/greet-stream?name=YourName'"
    echo ""
    echo "Press Ctrl+C to stop the application"
    echo ""
    
    mvn spring-boot:run
fi