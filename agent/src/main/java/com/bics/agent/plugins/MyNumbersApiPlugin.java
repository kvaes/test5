package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * MyNumbers API plugin for number management services.
 * This plugin provides access to the BICS MyNumbers API endpoints for managing
 * phone numbers and related operations.
 */
public class MyNumbersApiPlugin extends BaseApiPlugin {
    
    public MyNumbersApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getMyNumbersApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "MyNumbersAPI";
    }
    
    /**
     * Retrieves a list of phone numbers.
     * 
     * @param limit Maximum number of numbers to return (optional)
     * @param offset Number of numbers to skip (optional)
     * @return JSON string containing the list of phone numbers
     * @throws PluginException if the request fails
     */
    public String getNumbers(String limit, String offset) throws PluginException {
        
        StringBuilder queryParams = new StringBuilder();
        if (limit != null && !limit.isEmpty()) {
            queryParams.append("limit=").append(limit);
        }
        if (offset != null && !offset.isEmpty()) {
            if (queryParams.length() > 0) {
                queryParams.append("&");
            }
            queryParams.append("offset=").append(offset);
        }
        
        return performGet("/numbers", queryParams.toString());
    }
    
    /**
     * Retrieves details of a specific phone number.
     * 
     * @param phoneNumber The phone number to retrieve details for
     * @return JSON string containing the phone number details
     * @throws PluginException if the request fails
     */
    public String getNumberDetails(String phoneNumber) throws PluginException {
        
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new PluginException(getPluginName(), "getNumberDetails", "Phone number is required");
        }
        
        return performGet("/numbers/" + phoneNumber);
    }
    
    /**
     * Reserves a new phone number.
     * 
     * @param reservationData JSON string containing the reservation data
     * @return JSON string containing the reservation details
     * @throws PluginException if the request fails
     */
    public String reserveNumber(String reservationData) throws PluginException {
        
        if (reservationData == null || reservationData.isEmpty()) {
            throw new PluginException(getPluginName(), "reserveNumber", "Reservation data is required");
        }
        
        return performPost("/numbers/reserve", reservationData);
    }
    
    /**
     * Activates a reserved phone number.
     * 
     * @param phoneNumber The phone number to activate
     * @param activationData JSON string containing the activation data
     * @return JSON string containing the activation confirmation
     * @throws PluginException if the request fails
     */
    public String activateNumber(String phoneNumber, String activationData) throws PluginException {
        
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new PluginException(getPluginName(), "activateNumber", "Phone number is required");
        }
        
        return performPost("/numbers/" + phoneNumber + "/activate", activationData);
    }
    
    /**
     * Deactivates a phone number.
     * 
     * @param phoneNumber The phone number to deactivate
     * @return JSON string containing the deactivation confirmation
     * @throws PluginException if the request fails
     */
    public String deactivateNumber(String phoneNumber) throws PluginException {
        
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new PluginException(getPluginName(), "deactivateNumber", "Phone number is required");
        }
        
        return performPost("/numbers/" + phoneNumber + "/deactivate", "{}");
    }
}