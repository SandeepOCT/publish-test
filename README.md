# Test NPM Library

This is a test NPM library created for demonstration purposes. It provides basic functionality to showcase how to publish and use an NPM package.

## New: gRPC Spring Boot Implementation

This repository now also includes a complete **gRPC client and server implementation using Spring Boot**. 

### Quick Start with gRPC

```bash
# Build and run the gRPC application
./run.sh

# Or manually:
mvn clean compile
mvn test
mvn spring-boot:run
```

**Test the gRPC service via REST API:**
```bash
# Simple greeting
curl "http://localhost:8080/greet?name=World"

# Server streaming
curl "http://localhost:8080/greet-stream?name=Stream"
```

For detailed gRPC documentation, see [GRPC_README.md](./GRPC_README.md).

---

## Original NPM Library

### Installation

Install the library using npm:

```bash
npm install test-npm-library
```

### Usage

Import the library and use its functionality:

```javascript
const testLibrary = require('test-npm-library');

// Example usage
testLibrary.exampleFunction();
```

### Features

- Example function to demonstrate usage.
- Lightweight and easy to use.
- **NEW**: Complete gRPC implementation with Spring Boot

### Development

To contribute or modify the library:

1. Clone the repository:
  ```bash
  git clone https://github.com/your-username/test-npm-library.git
  ```
2. Install dependencies:
  ```bash
  npm install
  ```
3. Make your changes and test locally.

### Publishing

To publish a new version of the library:

1. Update the version in `package.json`.
2. Run the following command:
  ```bash
  npm publish
  ```

### License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.