package com.bics.agent.exceptions;

/**
 * Exception thrown when configuration loading or processing fails.
 * This exception is a specialized type of AgentException for configuration-related errors.
 */
public class ConfigurationException extends AgentException {
    
    /**
     * Constructs a new ConfigurationException with the specified detail message.
     * 
     * @param message the detail message
     */
    public ConfigurationException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ConfigurationException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new ConfigurationException with the specified cause.
     * 
     * @param cause the cause of the exception
     */
    public ConfigurationException(Throwable cause) {
        super(cause);
    }
}