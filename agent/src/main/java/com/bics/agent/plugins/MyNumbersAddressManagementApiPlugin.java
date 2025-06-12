package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers Address Management API plugin.
 * This plugin provides access to address management services for phone numbers.
 */
public class MyNumbersAddressManagementApiPlugin extends BaseApiPlugin {
    
    public MyNumbersAddressManagementApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersAddressManagementApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersAddressManagementAPI";
    }
    
    public String getAddresses(String phoneNumber) throws PluginException {
        return performGet("/addresses/" + phoneNumber);
    }
    
    public String updateAddress(String phoneNumber, String addressData) throws PluginException {
        return performPut("/addresses/" + phoneNumber, addressData);
    }
}