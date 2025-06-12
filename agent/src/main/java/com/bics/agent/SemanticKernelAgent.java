package com.bics.agent;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.AgentException;
import com.bics.agent.plugins.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Main Semantic Kernel Agent class that coordinates the initialization and operation
 * of all BICS API plugins. This class provides a framework ready for Semantic Kernel integration.
 */
public class SemanticKernelAgent {
    private static final Logger logger = LoggerFactory.getLogger(SemanticKernelAgent.class);
    
    private final AgentConfiguration configuration;
    private final Map<String, Object> plugins = new HashMap<>();
    private boolean isRunning = false;
    
    public SemanticKernelAgent(AgentConfiguration configuration) {
        this.configuration = configuration;
    }
    
    /**
     * Starts the Semantic Kernel Agent and initializes all plugins.
     * 
     * @throws AgentException if the agent fails to start
     */
    public void start() throws AgentException {
        try {
            logger.info("Initializing plugin framework...");
            
            logger.info("Registering BICS API plugins...");
            registerPlugins();
            
            isRunning = true;
            logger.info("Agent started successfully with {} plugins", plugins.size());
            
        } catch (Exception e) {
            throw new AgentException("Failed to start Agent", e);
        }
    }
    
    /**
     * Stops the Agent and cleans up resources.
     */
    public void stop() {
        if (isRunning) {
            logger.info("Stopping Agent...");
            // Cleanup resources if needed
            isRunning = false;
            logger.info("Agent stopped");
        }
    }
    
    /**
     * Checks if the agent is currently running.
     * 
     * @return true if the agent is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Gets a plugin by name.
     * 
     * @param pluginName the name of the plugin
     * @return the plugin instance or null if not found
     */
    public Object getPlugin(String pluginName) {
        return plugins.get(pluginName);
    }
    
    /**
     * Gets all registered plugins.
     * 
     * @return map of plugin names to plugin instances
     */
    public Map<String, Object> getAllPlugins() {
        return new HashMap<>(plugins);
    }
    
    private void registerPlugins() throws AgentException {
        try {
            // Register all BICS API plugins
            registerPlugin("ConnectAPI", new ConnectApiPlugin(configuration));
            registerPlugin("MyNumbersAPI", new MyNumbersApiPlugin(configuration));
            registerPlugin("MyNumbersAddressManagementAPI", new MyNumbersAddressManagementApiPlugin(configuration));
            registerPlugin("MyNumbersCDRAPI", new MyNumbersCdrApiPlugin(configuration));
            registerPlugin("MyNumbersDisconnectionAPI", new MyNumbersDisconnectionApiPlugin(configuration));
            registerPlugin("MyNumbersEmergencyServicesAPI", new MyNumbersEmergencyServicesApiPlugin(configuration));
            registerPlugin("MyNumbersNumberPortingAPI", new MyNumbersNumberPortingApiPlugin(configuration));
            registerPlugin("SMSAPI", new SmsApiPlugin(configuration));
            
            logger.info("All BICS API plugins registered successfully");
            
        } catch (Exception e) {
            throw new AgentException("Failed to register plugins", e);
        }
    }
    
    private void registerPlugin(String pluginName, Object pluginInstance) {
        try {
            plugins.put(pluginName, pluginInstance);
            logger.debug("Registered plugin: {}", pluginName);
        } catch (Exception e) {
            logger.error("Failed to register plugin {}: {}", pluginName, e.getMessage(), e);
            throw new RuntimeException("Failed to register plugin: " + pluginName, e);
        }
    }
}