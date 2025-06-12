package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers Number Porting API plugin.
 * This plugin provides access to number porting services for phone numbers.
 */
public class MyNumbersNumberPortingApiPlugin extends BaseApiPlugin {
    
    public MyNumbersNumberPortingApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersNumberPortingApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersNumberPortingAPI";
    }
    
    public String requestPortIn(
            String phoneNumber,
            String portingData) throws PluginException {
        return performPost("/portin/" + phoneNumber, portingData);
    }
    
    public String requestPortOut(
            String phoneNumber,
            String portingData) throws PluginException {
        return performPost("/portout/" + phoneNumber, portingData);
    }
    
    public String getPortingStatus(
            String phoneNumber) throws PluginException {
        return performGet("/porting/status/" + phoneNumber);
    }
    
    public String cancelPorting(
            String phoneNumber) throws PluginException {
        return performDelete("/porting/" + phoneNumber);
    }
}