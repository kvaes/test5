package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers Emergency Services API plugin.
 * This plugin provides access to emergency services management for phone numbers.
 */
public class MyNumbersEmergencyServicesApiPlugin extends BaseApiPlugin {
    
    public MyNumbersEmergencyServicesApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersEmergencyServicesApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersEmergencyServicesAPI";
    }
    
    public String registerEmergencyService(
            String phoneNumber,
            String emergencyData) throws PluginException {
        return performPost("/emergency/" + phoneNumber, emergencyData);
    }
    
    public String getEmergencyServiceInfo(
            String phoneNumber) throws PluginException {
        return performGet("/emergency/" + phoneNumber);
    }
    
    public String updateEmergencyService(
            String phoneNumber,
            String emergencyData) throws PluginException {
        return performPut("/emergency/" + phoneNumber, emergencyData);
    }
}