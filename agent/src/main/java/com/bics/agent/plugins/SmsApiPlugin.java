package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * SMS API plugin for SMS messaging services.
 * This plugin provides access to SMS sending and management operations.
 */
public class SmsApiPlugin extends BaseApiPlugin {
    
    public SmsApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getSmsApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "SMSAPI";
    }
    
    public String sendSms(
            String from,
            String to,
            String message) throws PluginException {
        
        String requestBody = String.format("{\"from\":\"%s\",\"to\":\"%s\",\"message\":\"%s\"}", from, to, message);
        return performPost("/sms/send", requestBody);
    }
    
    public String getSmsStatus(
            String messageId) throws PluginException {
        return performGet("/sms/status/" + messageId);
    }
    
    public String getSmsHistory(
            String phoneNumber,
            String limit) throws PluginException {
        
        StringBuilder queryParams = new StringBuilder();
        if (limit != null && !limit.isEmpty()) {
            queryParams.append("limit=").append(limit);
        }
        
        return performGet("/sms/history/" + phoneNumber, queryParams.toString());
    }
    
    public String sendBulkSms(
            String bulkSmsData) throws PluginException {
        return performPost("/sms/bulk", bulkSmsData);
    }
}