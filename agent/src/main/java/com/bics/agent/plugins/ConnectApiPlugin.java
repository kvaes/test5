package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;

/**
 * Connect API plugin for customer and product management operations.
 * This plugin provides access to the BICS Connect API endpoints for managing
 * customers, products, and related operations.
 */
public class ConnectApiPlugin extends BaseApiPlugin {
    
    public ConnectApiPlugin(AgentConfiguration configuration) {
        super(configuration);
    }
    
    @Override
    protected String getBaseUrl() {
        return configuration.getApiEndpoints().getConnectApiUrl();
    }
    
    @Override
    protected String getPluginName() {
        return "ConnectAPI";
    }
    
    /**
     * Retrieves a list of customers.
     * 
     * @param limit Maximum number of customers to return (optional)
     * @param offset Number of customers to skip (optional)
     * @return JSON string containing the list of customers
     * @throws PluginException if the request fails
     */
    public String getCustomers(String limit, String offset) throws PluginException {
        
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
        
        return performGet("/customers", queryParams.toString());
    }
    
    /**
     * Retrieves details of a specific customer by ID.
     * 
     * @param customerId The unique identifier of the customer
     * @return JSON string containing the customer details
     * @throws PluginException if the request fails
     */
    public String getCustomerById(String customerId) throws PluginException {
        
        if (customerId == null || customerId.isEmpty()) {
            throw new PluginException(getPluginName(), "getCustomerById", "Customer ID is required");
        }
        
        return performGet("/customers/" + customerId);
    }
    
    /**
     * Creates a new customer.
     * 
     * @param customerData JSON string containing the customer data
     * @return JSON string containing the created customer details
     * @throws PluginException if the request fails
     */
    public String createCustomer(String customerData) throws PluginException {
        
        if (customerData == null || customerData.isEmpty()) {
            throw new PluginException(getPluginName(), "createCustomer", "Customer data is required");
        }
        
        return performPost("/customers", customerData);
    }
    
    /**
     * Updates an existing customer.
     * 
     * @param customerId The unique identifier of the customer
     * @param customerData JSON string containing the updated customer data
     * @return JSON string containing the updated customer details
     * @throws PluginException if the request fails
     */
    public String updateCustomer(String customerId, String customerData) throws PluginException {
        
        if (customerId == null || customerId.isEmpty()) {
            throw new PluginException(getPluginName(), "updateCustomer", "Customer ID is required");
        }
        
        if (customerData == null || customerData.isEmpty()) {
            throw new PluginException(getPluginName(), "updateCustomer", "Customer data is required");
        }
        
        return performPut("/customers/" + customerId, customerData);
    }
    
    /**
     * Deletes a customer by ID.
     * 
     * @param customerId The unique identifier of the customer
     * @return JSON string containing the deletion confirmation
     * @throws PluginException if the request fails
     */
    public String deleteCustomer(String customerId) throws PluginException {
        
        if (customerId == null || customerId.isEmpty()) {
            throw new PluginException(getPluginName(), "deleteCustomer", "Customer ID is required");
        }
        
        return performDelete("/customers/" + customerId);
    }
    
    /**
     * Retrieves a list of products.
     * 
     * @param limit Maximum number of products to return (optional)
     * @param offset Number of products to skip (optional)
     * @return JSON string containing the list of products
     * @throws PluginException if the request fails
     */
    public String getProducts(String limit, String offset) throws PluginException {
        
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
        
        return performGet("/products", queryParams.toString());
    }
    
    /**
     * Retrieves details of a specific product by ID.
     * 
     * @param productId The unique identifier of the product
     * @return JSON string containing the product details
     * @throws PluginException if the request fails
     */
    public String getProductById(String productId) throws PluginException {
        
        if (productId == null || productId.isEmpty()) {
            throw new PluginException(getPluginName(), "getProductById", "Product ID is required");
        }
        
        return performGet("/products/" + productId);
    }
}