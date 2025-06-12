package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers Disconnection API plugin for number disconnection services.
 * This plugin provides access to phone number disconnection operations.
 */
public class MyNumbersDisconnectionApiPlugin extends BaseApiPlugin {
    
    public MyNumbersDisconnectionApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersDisconnectionApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersDisconnectionAPI";
    }
    
    public String requestDisconnection(
            String phoneNumber,
            String disconnectionData) throws PluginException {
        return performPost("/disconnect/" + phoneNumber, disconnectionData);
    }
    
    public String getDisconnectionStatus(
            String phoneNumber) throws PluginException {
        return performGet("/disconnect/status/" + phoneNumber);
    }
    
    public String cancelDisconnection(
            String phoneNumber) throws PluginException {
        return performDelete("/disconnect/" + phoneNumber);
    }
}