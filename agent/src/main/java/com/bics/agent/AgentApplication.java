package com.bics.agent;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.AgentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class for the BICS Semantic Kernel Agent.
 * This class serves as the entry point for the Java-based Semantic Kernel agent
 * that integrates with various BICS APIs.
 */
public class AgentApplication {
    private static final Logger logger = LoggerFactory.getLogger(AgentApplication.class);

    public static void main(String[] args) {
        try {
            logger.info("Starting BICS Semantic Kernel Agent...");
            
            // Initialize configuration
            AgentConfiguration config = AgentConfiguration.getInstance();
            logger.info("Configuration loaded successfully");
            
            // Initialize and start the agent
            SemanticKernelAgent agent = new SemanticKernelAgent(config);
            agent.start();
            
            logger.info("BICS Semantic Kernel Agent started successfully");
            
            // Keep the application running
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutting down BICS Semantic Kernel Agent...");
                agent.stop();
                logger.info("BICS Semantic Kernel Agent stopped");
            }));
            
        } catch (AgentException e) {
            logger.error("Failed to start BICS Semantic Kernel Agent: {}", e.getMessage(), e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("Unexpected error during startup: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
}