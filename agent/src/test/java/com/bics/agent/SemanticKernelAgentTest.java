package com.bics.agent;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.AgentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SemanticKernelAgent class.
 */
@ExtendWith(MockitoExtension.class)
public class SemanticKernelAgentTest {
    
    private SemanticKernelAgent agent;
    private AgentConfiguration configuration;
    
    @BeforeEach
    void setUp() throws Exception {
        configuration = AgentConfiguration.getInstance();
        agent = new SemanticKernelAgent(configuration);
    }
    
    @Test
    void shouldStartAndStopAgent() throws AgentException {
        // Given
        assertFalse(agent.isRunning());
        
        // When
        agent.start();
        
        // Then
        assertTrue(agent.isRunning());
        assertEquals(8, agent.getAllPlugins().size()); // All 8 API plugins
        
        // When
        agent.stop();
        
        // Then
        assertFalse(agent.isRunning());
    }
    
    @Test
    void shouldRegisterAllPlugins() throws AgentException {
        // When
        agent.start();
        
        // Then
        assertNotNull(agent.getPlugin("ConnectAPI"));
        assertNotNull(agent.getPlugin("MyNumbersAPI"));
        assertNotNull(agent.getPlugin("MyNumbersAddressManagementAPI"));
        assertNotNull(agent.getPlugin("MyNumbersCDRAPI"));
        assertNotNull(agent.getPlugin("MyNumbersDisconnectionAPI"));
        assertNotNull(agent.getPlugin("MyNumbersEmergencyServicesAPI"));
        assertNotNull(agent.getPlugin("MyNumbersNumberPortingAPI"));
        assertNotNull(agent.getPlugin("SMSAPI"));
        
        agent.stop();
    }
    
    @Test
    void shouldReturnNullForNonExistentPlugin() throws AgentException {
        // When
        agent.start();
        
        // Then
        assertNull(agent.getPlugin("NonExistentAPI"));
        
        agent.stop();
    }
}