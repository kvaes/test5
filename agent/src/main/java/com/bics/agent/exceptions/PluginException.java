package com.bics.agent.exceptions;

/**
 * Exception thrown when API plugin operations fail.
 * This exception is a specialized type of AgentException for API-related errors.
 */
public class PluginException extends AgentException {
    
    private final String pluginName;
    private final String operation;
    
    /**
     * Constructs a new PluginException with the specified detail message.
     * 
     * @param message the detail message
     */
    public PluginException(String message) {
        super(message);
        this.pluginName = null;
        this.operation = null;
    }
    
    /**
     * Constructs a new PluginException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public PluginException(String message, Throwable cause) {
        super(message, cause);
        this.pluginName = null;
        this.operation = null;
    }
    
    /**
     * Constructs a new PluginException with plugin context information.
     * 
     * @param pluginName the name of the plugin that caused the error
     * @param operation the operation that failed
     * @param message the detail message
     */
    public PluginException(String pluginName, String operation, String message) {
        super(String.format("Plugin '%s' operation '%s' failed: %s", pluginName, operation, message));
        this.pluginName = pluginName;
        this.operation = operation;
    }
    
    /**
     * Constructs a new PluginException with plugin context information and cause.
     * 
     * @param pluginName the name of the plugin that caused the error
     * @param operation the operation that failed
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public PluginException(String pluginName, String operation, String message, Throwable cause) {
        super(String.format("Plugin '%s' operation '%s' failed: %s", pluginName, operation, message), cause);
        this.pluginName = pluginName;
        this.operation = operation;
    }
    
    /**
     * Gets the name of the plugin that caused the error.
     * 
     * @return the plugin name, or null if not specified
     */
    public String getPluginName() {
        return pluginName;
    }
    
    /**
     * Gets the operation that failed.
     * 
     * @return the operation name, or null if not specified
     */
    public String getOperation() {
        return operation;
    }
}