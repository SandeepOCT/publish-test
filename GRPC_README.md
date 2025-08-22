# gRPC Spring Boot Application

This project demonstrates a complete implementation of gRPC client and server using Spring Boot.

## Features

- **gRPC Server**: Implements a greeting service with both unary and server streaming RPCs
- **gRPC Client**: Demonstrates how to call gRPC services from within a Spring Boot application
- **REST API**: Provides HTTP endpoints that internally use the gRPC client
- **Integration Tests**: Includes tests to verify the gRPC functionality

## Project Structure

```
src/
├── main/
│   ├── java/com/sandeep/grpc/
│   │   ├── GrpcApplication.java              # Main Spring Boot application
│   │   ├── client/
│   │   │   ├── GreetingClient.java           # gRPC client implementation
│   │   │   └── GreetingController.java       # REST controller using gRPC client
│   │   └── server/
│   │       └── GreetingServiceImpl.java      # gRPC server implementation
│   ├── proto/
│   │   └── greeting.proto                    # Protocol Buffer definitions
│   └── resources/
│       └── application.properties            # Application configuration
└── test/
    └── java/com/sandeep/grpc/
        └── GrpcApplicationTests.java          # Integration tests
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Build and Run

1. **Compile the project:**
   ```bash
   mvn clean compile
   ```

2. **Run the tests:**
   ```bash
   mvn test
   ```

3. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

### Usage

Once the application is running:

- **gRPC Server** runs on port `9090`
- **HTTP Server** runs on port `8080`

#### Test via REST API

1. **Simple greeting:**
   ```bash
   curl "http://localhost:8080/greet?name=John"
   ```
   Response: `Hello, John! Welcome to gRPC with Spring Boot.`

2. **Server streaming greeting:**
   ```bash
   curl "http://localhost:8080/greet-stream?name=Jane"
   ```
   Response: `Check logs for streaming responses for: Jane`
   
   Check the application logs to see the streaming responses.

#### Test via gRPC Client

You can also test the gRPC service directly using gRPC clients or tools like `grpcurl`:

```bash
# Install grpcurl (if not already installed)
# brew install grpcurl  # on macOS
# or download from: https://github.com/fullstorydev/grpcurl

# Test unary RPC
grpcurl -plaintext -d '{"name":"World"}' localhost:9090 com.sandeep.grpc.GreetingService/SayHello

# Test server streaming RPC
grpcurl -plaintext -d '{"name":"World"}' localhost:9090 com.sandeep.grpc.GreetingService/SayHelloServerStreaming
```

## Configuration

The application can be configured via `application.properties`:

```properties
# HTTP Server port
server.port=8080

# gRPC Server port
grpc.server.port=9090

# gRPC Client configuration
grpc.client.local-grpc-server.address=static://localhost:9090
grpc.client.local-grpc-server.negotiation-type=plaintext
```

## Service Definition

The gRPC service is defined in `src/main/proto/greeting.proto`:

```protobuf
service GreetingService {
  // Simple greeting RPC
  rpc SayHello (HelloRequest) returns (HelloResponse);
  
  // Server streaming RPC
  rpc SayHelloServerStreaming (HelloRequest) returns (stream HelloResponse);
}
```

## Dependencies

Key dependencies used in this project:

- **Spring Boot Starter**: Core Spring Boot functionality
- **gRPC Spring Boot Starter**: Integration between gRPC and Spring Boot
- **Protocol Buffers**: Message serialization
- **gRPC Java**: gRPC implementation for Java

## Development

To extend this project:

1. **Add new RPC methods**: Update `greeting.proto` and regenerate Java classes
2. **Implement server methods**: Add implementations in `GreetingServiceImpl`
3. **Create client methods**: Add client calls in `GreetingClient`
4. **Add tests**: Create tests in the test directory

## Monitoring

The application includes:

- **Health checks**: Available via gRPC health service
- **Reflection**: gRPC reflection service for introspection
- **Logging**: Detailed logging for debugging

## Notes

This implementation demonstrates:

- **Unary RPCs**: Simple request-response pattern
- **Server streaming**: Server sends multiple responses for a single request
- **Spring Boot integration**: Seamless integration with Spring's dependency injection
- **Configuration**: Externalized configuration via properties files
- **Testing**: Integration testing with Spring Boot Test