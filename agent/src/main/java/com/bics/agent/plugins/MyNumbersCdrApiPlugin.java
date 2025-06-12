package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers CDR API plugin for Call Detail Record services.
 * This plugin provides access to call detail records and usage information.
 */
public class MyNumbersCdrApiPlugin extends BaseApiPlugin {
    
    public MyNumbersCdrApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersCdrApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersCDRAPI";
    }
    
    public String getCdrRecords(
            String phoneNumber,
            String startDate,
            String endDate) throws PluginException {
        
        StringBuilder queryParams = new StringBuilder();
        if (startDate != null) queryParams.append("startDate=").append(startDate);
        if (endDate != null) {
            if (queryParams.length() > 0) queryParams.append("&");
            queryParams.append("endDate=").append(endDate);
        }
        
        return performGet("/cdr/" + phoneNumber, queryParams.toString());
    }
    
    public String getUsageSummary(
            String phoneNumber) throws PluginException {
        return performGet("/usage/" + phoneNumber);
    }
}