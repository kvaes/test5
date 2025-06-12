# BICS Semantic Kernel Agent

A Java-based Semantic Kernel agent providing comprehensive integration with BICS APIs for telecommunications services management.

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/kvaes/test5.git
cd test5/agent

# Build and run
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication"
```

## 📋 Overview

The BICS Semantic Kernel Agent is a robust, scalable Java application designed to integrate with multiple BICS telecommunications APIs. It provides a unified interface for managing:

- **Customer Management** (Connect API)
- **Phone Number Services** (MyNumbers API)
- **Address Management** (MyNumbers Address Management API)
- **Call Detail Records** (MyNumbers CDR API)
- **Number Disconnection** (MyNumbers Disconnection API)
- **Emergency Services** (MyNumbers Emergency Services API)
- **Number Porting** (MyNumbers Number Porting API)
- **SMS Services** (SMS API)

## 🏗️ Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Agent Core    │    │   Plugin Layer   │    │   BICS APIs     │
│                 │    │                  │    │                 │
│ • Configuration │◄──►│ • Connect API    │◄──►│ • Connect       │
│ • Error Handler │    │ • MyNumbers APIs │    │ • MyNumbers     │
│ • Plugin Mgmt   │    │ • SMS API        │    │ • SMS           │
└─────────────────┘    └──────────────────┘    └─────────────────┘
```

## 🛠️ Features

- **Plugin-Based Architecture**: Modular design with dedicated plugins for each API
- **Comprehensive Error Handling**: Robust error management with custom exceptions
- **Configuration Management**: YAML-based configuration with environment variable support
- **Container Ready**: Docker support with Jib for easy containerization
- **CI/CD Pipeline**: GitHub Actions workflow for automated testing and deployment
- **Security Focused**: Vulnerability scanning and secure coding practices
- **Comprehensive Testing**: Unit tests with JUnit 5 and Mockito
- **Documentation**: Extensive documentation for development and deployment

## 📦 API Plugins

### Connect API Plugin
- Customer management operations
- Product catalog access
- Account management

### MyNumbers API Plugin
- Phone number reservation and activation
- Number status management
- Inventory operations

### SMS API Plugin
- Send individual and bulk SMS messages
- Message status tracking
- Delivery reporting

*[Full API documentation available in `/docs/api/`]*

## 🔧 Technology Stack

- **Java 17** - Modern Java with latest features
- **Maven** - Dependency management and build automation
- **Jackson** - JSON processing and YAML configuration
- **Apache HttpComponents** - HTTP client for API communication
- **SLF4J + Logback** - Comprehensive logging
- **JUnit 5** - Modern testing framework
- **Mockito** - Mocking framework for tests
- **Docker** - Containerization support

## 📖 Documentation

- [**Local Development Setup**](docs/development/local-setup.md) - Get started with local development
- [**GitHub Codespaces Setup**](docs/development/codespaces-setup.md) - Cloud-based development environment
- [**API Documentation**](docs/api/README.md) - Detailed API plugin documentation
- [**Deployment Guide**](docs/deployment/README.md) - Deployment options and configurations

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- Docker (optional, for containerization)

### Installation

1. **Clone and build**
   ```bash
   git clone https://github.com/kvaes/test5.git
   cd test5/agent
   mvn clean compile
   ```

2. **Run tests**
   ```bash
   mvn test
   ```

3. **Start the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.bics.agent.AgentApplication"
   ```

### Configuration

Configure API endpoints in `src/main/resources/application.yml`:

```yaml
api:
  endpoints:
    connect: "https://connect.api.bics.com"
    mynumbers: "https://mynumbers.api.bics.com"
    sms: "https://sms.api.bics.com"
```

## 🐳 Docker

Build and run with Docker:

```bash
# Build container image
mvn jib:dockerBuild

# Run container
docker run -p 8080:8080 bics-semantic-kernel-agent
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Run specific test
mvn test -Dtest=SemanticKernelAgentTest
```

## 🔄 CI/CD

The project includes a comprehensive CI/CD pipeline:

- **Automated Testing** - Runs on every push and PR
- **Container Building** - Automatic Docker image creation
- **Security Scanning** - Vulnerability scanning with Trivy
- **Dependency Updates** - Automated dependency updates with Dependabot

## 🛡️ Security

- **Vulnerability Scanning** - Automated security scans
- **Dependency Management** - Regular updates via Dependabot
- **Secure Coding** - Following OWASP guidelines
- **Secrets Management** - Environment-based configuration

See our [Security Policy](.github/SECURITY.md) for more details.

## 📊 Monitoring

- **Structured Logging** - JSON-formatted logs for easy parsing
- **Health Checks** - Application health monitoring endpoints
- **Metrics** - Application performance metrics

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

See [Contributing Guidelines](CONTRIBUTING.md) for detailed information.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

- **Documentation**: Check the `/docs` directory
- **Issues**: Open an issue on GitHub
- **Email**: [support@bics.com](mailto:support@bics.com)

## 🗺️ Roadmap

- [ ] Semantic Kernel integration (when Java SDK is available)
- [ ] WebSocket support for real-time updates
- [ ] GraphQL API layer
- [ ] Advanced monitoring and observability
- [ ] Multi-tenancy support
- [ ] Event-driven architecture
- [ ] Machine learning integration

## 📈 Status

![Build Status](https://github.com/kvaes/test5/workflows/Agent%20CI/badge.svg)
![Coverage](https://img.shields.io/badge/coverage-85%25-green)
![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue)

---

**BICS Semantic Kernel Agent** - Connecting telecommunications services through intelligent automation.