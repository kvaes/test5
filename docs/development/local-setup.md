# Local Development Setup

This guide will help you set up the BICS Semantic Kernel Agent for local development.

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- Docker (for containerization)
- IDE of your choice (IntelliJ IDEA, Eclipse, VS Code)

## Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/kvaes/test5.git
   cd test5/agent
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication"
   ```

## Project Structure

```
agent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bics/agent/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── exceptions/      # Custom exceptions
│   │   │       ├── models/          # Data models
│   │   │       ├── plugins/         # API plugin implementations
│   │   │       └── utils/           # Utility classes
│   │   └── resources/
│   │       └── application.yml      # Configuration file
│   └── test/
│       └── java/                    # Unit tests
├── pom.xml                          # Maven configuration
└── Dockerfile                       # Container configuration
```

## Configuration

The application uses a YAML configuration file located at `src/main/resources/application.yml`. Key configuration sections include:

- **API Endpoints**: Base URLs for all BICS APIs
- **HTTP Settings**: Timeout and retry configurations
- **Logging**: Log levels and patterns

### Environment Variables

You can override configuration using environment variables:

```bash
export API_ENDPOINTS_CONNECT=https://custom-connect-api.com
export HTTP_TIMEOUT_CONNECTION=45000
export LOGGING_LEVEL_ROOT=DEBUG
```

## Development Workflow

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Follow Java coding conventions
   - Add unit tests for new functionality
   - Update documentation as needed

3. **Test your changes**
   ```bash
   mvn clean test
   mvn clean compile
   ```

4. **Build the container**
   ```bash
   mvn jib:dockerBuild
   ```

5. **Commit and push**
   ```bash
   git add .
   git commit -m "Add your descriptive commit message"
   git push origin feature/your-feature-name
   ```

## IDE Setup

### IntelliJ IDEA

1. Open the `agent` folder as a project
2. Configure JDK 17 in Project Settings
3. Import Maven dependencies
4. Configure code style (use Google Java Style)

### VS Code

1. Install Java Extension Pack
2. Open the `agent` folder
3. Configure Java 17 in settings
4. Install Maven for Java extension

## Debugging

To run the application in debug mode:

```bash
mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication" -Dexec.debug
```

Or use your IDE's debugging capabilities.

## Testing

### Unit Tests

Run all unit tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=SemanticKernelAgentTest
```

### Integration Tests

Integration tests are planned for future implementation.

## Container Development

Build the Docker image:
```bash
mvn jib:dockerBuild
```

Run the container:
```bash
docker run -p 8080:8080 bics-semantic-kernel-agent
```

## Troubleshooting

### Common Issues

1. **Maven dependencies not downloading**
   - Check internet connection
   - Clear Maven cache: `mvn dependency:purge-local-repository`

2. **Java version issues**
   - Ensure JAVA_HOME is set to JDK 17
   - Check with: `java -version` and `mvn -version`

3. **Configuration not loading**
   - Verify `application.yml` is in `src/main/resources`
   - Check file permissions

### Logs

Application logs are written to the console by default. To change log levels, modify the `application.yml` file:

```yaml
logging:
  level:
    com.bics.agent: DEBUG
```

## Next Steps

- Read the [API Documentation](../api/README.md)
- Learn about [Deployment](../deployment/README.md)
- Set up [GitHub Codespaces](./codespaces-setup.md)