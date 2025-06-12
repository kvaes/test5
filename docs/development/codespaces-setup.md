# GitHub Codespaces Setup

This guide explains how to set up and use GitHub Codespaces for end-to-end development and testing of the BICS Semantic Kernel Agent.

## What is GitHub Codespaces?

GitHub Codespaces provides a complete development environment in the cloud, accessible from your browser or VS Code. It comes pre-configured with all the tools and dependencies needed for this project.

## Setting Up Codespaces

### 1. Create a Codespace

1. Navigate to the repository on GitHub
2. Click the green "Code" button
3. Select the "Codespaces" tab
4. Click "Create codespace on main"

### 2. Wait for Environment Setup

The codespace will automatically:
- Install Java 17
- Install Maven
- Download project dependencies
- Set up VS Code extensions

## Configuration

The repository includes a `.devcontainer` configuration that sets up:

- Java 17 development environment
- Maven build tools
- Docker for containerization
- VS Code extensions for Java development
- Port forwarding for web services

## Development Workflow in Codespaces

### 1. Build the Project

```bash
cd agent
mvn clean compile
```

### 2. Run Tests

```bash
mvn test
```

### 3. Start the Application

```bash
mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication"
```

The application will be available on port 8080, which is automatically forwarded by Codespaces.

### 4. Build Container

```bash
mvn jib:dockerBuild
```

## End-to-End Testing

### Setting Up Multiple Services

1. **Terminal 1: Start the Agent**
   ```bash
   cd agent
   mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication"
   ```

2. **Terminal 2: Run API Tests**
   ```bash
   # Test Connect API plugin
   curl -X GET "http://localhost:8080/api/connect/customers?limit=10"
   ```

3. **Terminal 3: Monitor Logs**
   ```bash
   tail -f logs/application.log
   ```

### Testing Plugin Functionality

Each API plugin can be tested individually:

```bash
# Test Connect API
curl -X GET "http://localhost:8080/api/connect/customers"

# Test MyNumbers API
curl -X GET "http://localhost:8080/api/mynumbers/numbers"

# Test SMS API
curl -X POST "http://localhost:8080/api/sms/send" \
  -H "Content-Type: application/json" \
  -d '{"from":"+123456789","to":"+987654321","message":"Test"}'
```

## Configuration for Codespaces

### Environment Variables

Set up environment variables in your codespace:

```bash
# API Configuration
export API_ENDPOINTS_CONNECT=https://connect.api.bics.com
export API_ENDPOINTS_MYNUMBERS=https://mynumbers.api.bics.com
export API_ENDPOINTS_SMS=https://sms.api.bics.com

# Authentication (use test credentials)
export BICS_API_KEY=your-test-api-key
export BICS_CLIENT_ID=your-test-client-id
```

### Secrets Management

For sensitive data, use GitHub repository secrets:

1. Go to repository Settings > Secrets and variables > Codespaces
2. Add secrets like:
   - `BICS_API_KEY`
   - `BICS_CLIENT_SECRET`
   - `DATABASE_URL`

## Debugging in Codespaces

### 1. Use VS Code Debugger

1. Set breakpoints in your Java code
2. Press F5 or use the Debug panel
3. Select "Debug Java Application"

### 2. Remote Debugging

For Maven debugging:
```bash
mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication" \
  -Dexec.args="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

Then connect VS Code debugger to port 5005.

## Integration Testing

### Mock Services

For testing without real API endpoints, use the included mock services:

```bash
# Start mock BICS APIs
docker-compose -f docker-compose.test.yml up -d

# Run integration tests
mvn test -Dtest=*IntegrationTest
```

### Database Testing

If database integration is added:

```bash
# Start PostgreSQL container
docker run -d \
  --name postgres-test \
  -e POSTGRES_DB=bics_agent_test \
  -e POSTGRES_PASSWORD=test \
  -p 5432:5432 \
  postgres:15

# Run database tests
mvn test -Dspring.profiles.active=test
```

## Performance Testing

### Load Testing with JMeter

```bash
# Install JMeter
apt-get update && apt-get install -y jmeter

# Run load tests
jmeter -n -t tests/load-test.jmx -l results.jtl
```

### Memory Profiling

```bash
# Run with JProfiler
mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication" \
  -Dexec.args="-javaagent:jprofiler/bin/agent.jar"
```

## Collaboration

### Sharing Your Codespace

1. Click "Share" in the VS Code command palette
2. Generate a sharing link
3. Set appropriate permissions (read/write)

### Live Share

For real-time collaboration:
1. Install Live Share extension
2. Start a Live Share session
3. Share the link with team members

## Tips and Best Practices

### 1. Port Management

Monitor which ports are in use:
```bash
netstat -tulpn | grep LISTEN
```

### 2. Resource Management

Check resource usage:
```bash
htop  # Monitor CPU and memory
df -h # Check disk space
```

### 3. Git Configuration

Set up your Git identity:
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@bics.com"
```

### 4. Extension Recommendations

Useful VS Code extensions for this project:
- Java Extension Pack
- Spring Boot Extension Pack
- Docker
- REST Client
- Thunder Client (for API testing)

## Troubleshooting

### Common Issues

1. **Port conflicts**
   - Use different ports: `mvn exec:java -Dserver.port=8081`

2. **Out of memory**
   - Increase JVM memory: `export MAVEN_OPTS="-Xmx2g"`

3. **Slow startup**
   - Use faster Maven goal: `mvn spring-boot:run`

### Getting Help

- Check the [VS Code Codespaces documentation](https://docs.github.com/en/codespaces)
- Review project documentation in `/docs`
- Open an issue on GitHub for project-specific problems

## Cleanup

When finished:
1. Stop running processes (Ctrl+C)
2. Stop Docker containers: `docker stop $(docker ps -q)`
3. Delete the codespace from GitHub to free up usage hours

## Next Steps

- Learn about [Local Development Setup](./local-setup.md)
- Read the [API Documentation](../api/README.md)
- Explore [Deployment Options](../deployment/README.md)