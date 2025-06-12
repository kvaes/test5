package com.bics.agent.config;

import com.bics.agent.exceptions.ConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * Configuration management class for the BICS Semantic Kernel Agent.
 * This class loads and manages all configuration settings including API endpoints,
 * authentication details, and other runtime parameters.
 */
public class AgentConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AgentConfiguration.class);
    private static final String DEFAULT_CONFIG_FILE = "application.yml";
    
    private static AgentConfiguration instance;
    private Map<String, Object> config;
    private ApiEndpoints apiEndpoints;
    
    private AgentConfiguration() throws ConfigurationException {
        loadConfiguration();
    }
    
    /**
     * Gets the singleton instance of AgentConfiguration.
     * 
     * @return the AgentConfiguration instance
     * @throws ConfigurationException if configuration fails to load
     */
    public static synchronized AgentConfiguration getInstance() throws ConfigurationException {
        if (instance == null) {
            instance = new AgentConfiguration();
        }
        return instance;
    }
    
    /**
     * Gets the API endpoints configuration.
     * 
     * @return ApiEndpoints instance containing all API endpoint URLs
     */
    public ApiEndpoints getApiEndpoints() {
        return apiEndpoints;
    }
    
    /**
     * Gets a configuration value by key.
     * 
     * @param key the configuration key
     * @return the configuration value
     */
    public Object get(String key) {
        return config.get(key);
    }
    
    /**
     * Gets a configuration value by key with a default value.
     * 
     * @param key the configuration key
     * @param defaultValue the default value if key is not found
     * @return the configuration value or default value
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, T defaultValue) {
        return (T) config.getOrDefault(key, defaultValue);
    }
    
    private void loadConfiguration() throws ConfigurationException {
        try {
            logger.info("Loading configuration from {}", DEFAULT_CONFIG_FILE);
            
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            
            try (InputStream configStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE)) {
                if (configStream == null) {
                    throw new ConfigurationException("Configuration file not found: " + DEFAULT_CONFIG_FILE);
                }
                
                config = mapper.readValue(configStream, Map.class);
                logger.info("Configuration loaded successfully");
                
                // Initialize API endpoints
                initializeApiEndpoints();
                
            }
        } catch (Exception e) {
            throw new ConfigurationException("Failed to load configuration", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void initializeApiEndpoints() {
        Map<String, Object> endpoints = (Map<String, Object>) config.get("api.endpoints");
        if (endpoints != null) {
            this.apiEndpoints = new ApiEndpoints(endpoints);
            logger.debug("API endpoints initialized");
        } else {
            logger.warn("No API endpoints configuration found");
            this.apiEndpoints = new ApiEndpoints(Map.of());
        }
    }
    
    /**
     * API endpoints configuration class that holds all the API base URLs.
     */
    public static class ApiEndpoints {
        private final Map<String, Object> endpoints;
        
        public ApiEndpoints(Map<String, Object> endpoints) {
            this.endpoints = endpoints;
        }
        
        public String getConnectApiUrl() {
            return (String) endpoints.getOrDefault("connect", "https://connect.api.bics.com");
        }
        
        public String getMyNumbersApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers", "https://mynumbers.api.bics.com");
        }
        
        public String getMyNumbersAddressManagementApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers-address", "https://mynumbers-address.api.bics.com");
        }
        
        public String getMyNumbersCdrApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers-cdr", "https://mynumbers-cdr.api.bics.com");
        }
        
        public String getMyNumbersDisconnectionApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers-disconnection", "https://mynumbers-disconnection.api.bics.com");
        }
        
        public String getMyNumbersEmergencyServicesApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers-emergency", "https://mynumbers-emergency.api.bics.com");
        }
        
        public String getMyNumbersNumberPortingApiUrl() {
            return (String) endpoints.getOrDefault("mynumbers-porting", "https://mynumbers-porting.api.bics.com");
        }
        
        public String getSmsApiUrl() {
            return (String) endpoints.getOrDefault("sms", "https://sms.api.bics.com");
        }
    }
}