# API Documentation

This document provides comprehensive information about the BICS API plugins implemented in the Semantic Kernel Agent.

## Overview

The agent implements plugins for 8 different BICS APIs, each designed to handle specific telecommunications services. All plugins follow a consistent pattern and provide robust error handling.

## Plugin Architecture

### Base Plugin Pattern

All API plugins extend the `BaseApiPlugin` class which provides:

- HTTP client management
- Common error handling
- Request/response logging
- Configuration access

```java
public abstract class BaseApiPlugin {
    protected abstract String getBaseUrl();
    protected abstract String getPluginName();
    
    // HTTP methods: performGet, performPost, performPut, performDelete
}
```

### Error Handling

All plugins use consistent error handling through the `PluginException` class:

```java
public class PluginException extends AgentException {
    private final String pluginName;
    private final String operation;
}
```

## API Plugins

### 1. Connect API Plugin

**Purpose**: Customer and product management operations

**Base URL**: Configurable via `api.endpoints.connect`

**Available Methods**:

#### Customer Operations
- `getCustomers(limit, offset)` - Retrieve customer list
- `getCustomerById(customerId)` - Get specific customer details
- `createCustomer(customerData)` - Create new customer
- `updateCustomer(customerId, customerData)` - Update existing customer
- `deleteCustomer(customerId)` - Delete customer

#### Product Operations
- `getProducts(limit, offset)` - Retrieve product catalog
- `getProductById(productId)` - Get specific product details

**Example Usage**:
```java
ConnectApiPlugin connectPlugin = new ConnectApiPlugin(configuration);
String customers = connectPlugin.getCustomers("10", "0");
String customer = connectPlugin.getCustomerById("12345");
```

### 2. MyNumbers API Plugin

**Purpose**: Phone number management and lifecycle operations

**Base URL**: Configurable via `api.endpoints.mynumbers`

**Available Methods**:
- `getNumbers(limit, offset)` - List available phone numbers
- `getNumberDetails(phoneNumber)` - Get detailed number information
- `reserveNumber(reservationData)` - Reserve a phone number
- `activateNumber(phoneNumber, activationData)` - Activate reserved number
- `deactivateNumber(phoneNumber)` - Deactivate active number

**Example Usage**:
```java
MyNumbersApiPlugin numbersPlugin = new MyNumbersApiPlugin(configuration);
String numbers = numbersPlugin.getNumbers("50", "0");
String details = numbersPlugin.getNumberDetails("+1234567890");
```

### 3. MyNumbers Address Management API Plugin

**Purpose**: Address management for phone numbers

**Base URL**: Configurable via `api.endpoints.mynumbers-address`

**Available Methods**:
- `getAddresses(phoneNumber)` - Get addresses associated with a number
- `updateAddress(phoneNumber, addressData)` - Update address information

### 4. MyNumbers CDR API Plugin

**Purpose**: Call Detail Records and usage information

**Base URL**: Configurable via `api.endpoints.mynumbers-cdr`

**Available Methods**:
- `getCdrRecords(phoneNumber, startDate, endDate)` - Retrieve call records
- `getUsageSummary(phoneNumber)` - Get usage summary

### 5. MyNumbers Disconnection API Plugin

**Purpose**: Phone number disconnection services

**Base URL**: Configurable via `api.endpoints.mynumbers-disconnection`

**Available Methods**:
- `requestDisconnection(phoneNumber, disconnectionData)` - Request number disconnection
- `getDisconnectionStatus(phoneNumber)` - Check disconnection status
- `cancelDisconnection(phoneNumber)` - Cancel pending disconnection

### 6. MyNumbers Emergency Services API Plugin

**Purpose**: Emergency services management

**Base URL**: Configurable via `api.endpoints.mynumbers-emergency`

**Available Methods**:
- `registerEmergencyService(phoneNumber, emergencyData)` - Register emergency service
- `getEmergencyServiceInfo(phoneNumber)` - Get emergency service details
- `updateEmergencyService(phoneNumber, emergencyData)` - Update emergency information

### 7. MyNumbers Number Porting API Plugin

**Purpose**: Number porting services

**Base URL**: Configurable via `api.endpoints.mynumbers-porting`

**Available Methods**:
- `requestPortIn(phoneNumber, portingData)` - Request port-in
- `requestPortOut(phoneNumber, portingData)` - Request port-out
- `getPortingStatus(phoneNumber)` - Check porting status
- `cancelPorting(phoneNumber)` - Cancel porting request

### 8. SMS API Plugin

**Purpose**: SMS messaging services

**Base URL**: Configurable via `api.endpoints.sms`

**Available Methods**:
- `sendSms(from, to, message)` - Send individual SMS
- `getSmsStatus(messageId)` - Check message status
- `getSmsHistory(phoneNumber, limit)` - Get message history
- `sendBulkSms(bulkSmsData)` - Send bulk SMS messages

**Example Usage**:
```java
SmsApiPlugin smsPlugin = new SmsApiPlugin(configuration);
String result = smsPlugin.sendSms("+1234567890", "+0987654321", "Hello World");
String status = smsPlugin.getSmsStatus("msg-12345");
```

## Data Models

### Customer Model
```java
public class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String createdAt;
    private String updatedAt;
}
```

### Phone Number Model
```java
public class PhoneNumber {
    private String number;
    private String countryCode;
    private String status;
    private String type;
    private String assignedCustomerId;
    private String activationDate;
    private String expiryDate;
    private String[] features;
}
```

### SMS Message Model
```java
public class SmsMessage {
    private String messageId;
    private String from;
    private String to;
    private String message;
    private String status;
    private String sentAt;
    private String deliveredAt;
    private String errorCode;
    private String errorMessage;
}
```

## Configuration

### API Endpoints

All API endpoints are configurable via `application.yml`:

```yaml
api:
  endpoints:
    connect: "https://connect.api.bics.com"
    mynumbers: "https://mynumbers.api.bics.com"
    mynumbers-address: "https://mynumbers-address.api.bics.com"
    mynumbers-cdr: "https://mynumbers-cdr.api.bics.com"
    mynumbers-disconnection: "https://mynumbers-disconnection.api.bics.com"
    mynumbers-emergency: "https://mynumbers-emergency.api.bics.com"
    mynumbers-porting: "https://mynumbers-porting.api.bics.com"
    sms: "https://sms.api.bics.com"
```

### HTTP Configuration

```yaml
http:
  timeout:
    connection: 30000  # Connection timeout in milliseconds
    read: 60000        # Read timeout in milliseconds
  retry:
    maxAttempts: 3     # Maximum retry attempts
    backoffMs: 1000    # Initial backoff in milliseconds
```

## Authentication

Authentication configuration is placeholder for future implementation:

```yaml
auth:
  enabled: false
  # Future: API keys, OAuth settings, etc.
```

For production use, authentication should be implemented via:
- Environment variables for API keys
- OAuth 2.0 for secure token-based authentication
- Certificate-based authentication where required

## Error Handling

### Exception Hierarchy

```
AgentException
├── ConfigurationException
└── PluginException
```

### HTTP Error Handling

The base plugin handles HTTP errors consistently:

- **2xx responses**: Success, returns response body
- **4xx responses**: Client errors, throws PluginException with details
- **5xx responses**: Server errors, throws PluginException with retry information

### Example Error Response

```java
try {
    String result = connectPlugin.getCustomerById("invalid-id");
} catch (PluginException e) {
    logger.error("Plugin: {}, Operation: {}, Error: {}", 
        e.getPluginName(), e.getOperation(), e.getMessage());
}
```

## Testing

### Unit Testing

Each plugin includes comprehensive unit tests:

```java
@Test
void shouldRetrieveCustomers() throws PluginException {
    // Given
    when(httpClient.execute(any(), any())).thenReturn(mockResponse);
    
    // When
    String result = connectPlugin.getCustomers("10", "0");
    
    // Then
    assertNotNull(result);
    verify(httpClient).execute(any(), any());
}
```

### Integration Testing

Integration tests verify end-to-end plugin functionality with real or mock API endpoints.

## Monitoring and Observability

### Logging

All plugins provide structured logging:

```java
logger.debug("Performing GET request to: {}", url);
logger.info("Plugin {} operation {} completed successfully", pluginName, operation);
logger.error("Plugin {} operation {} failed: {}", pluginName, operation, error);
```

### Metrics

Future implementation will include:
- Request/response times
- Success/failure rates
- API quota usage
- Error rates by plugin

## Future Enhancements

1. **Semantic Kernel Integration**: Once Java SDK is available
2. **Async Operations**: Non-blocking API calls
3. **Circuit Breaker**: Fault tolerance patterns
4. **Rate Limiting**: API quota management
5. **Caching**: Response caching for improved performance
6. **Webhooks**: Event-driven updates

## Best Practices

1. **Always handle exceptions** when calling plugin methods
2. **Use appropriate timeouts** for long-running operations
3. **Validate input parameters** before API calls
4. **Log important operations** for troubleshooting
5. **Monitor API quotas** and usage limits
6. **Use configuration** for all environment-specific settings

## Support

For API-specific questions:
- Connect API: [connect-support@bics.com](mailto:connect-support@bics.com)
- MyNumbers APIs: [mynumbers-support@bics.com](mailto:mynumbers-support@bics.com)
- SMS API: [sms-support@bics.com](mailto:sms-support@bics.com)
- General Support: [support@bics.com](mailto:support@bics.com)