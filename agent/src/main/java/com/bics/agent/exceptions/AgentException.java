package com.bics.agent.exceptions;

/**
 * Base exception class for all agent-related exceptions.
 * This exception provides a consistent error handling mechanism
 * throughout the BICS Semantic Kernel Agent.
 */
public class AgentException extends Exception {
    
    /**
     * Constructs a new AgentException with the specified detail message.
     * 
     * @param message the detail message
     */
    public AgentException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new AgentException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public AgentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new AgentException with the specified cause.
     * 
     * @param cause the cause of the exception
     */
    public AgentException(Throwable cause) {
        super(cause);
    }
}