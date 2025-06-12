package com.bics.agent.plugins;

import com.bics.agent.config.AgentConfiguration;
import com.bics.agent.exceptions.PluginException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Base class for all BICS API plugins.
 * This class provides common functionality for HTTP communication,
 * error handling, and configuration management.
 */
public abstract class BaseApiPlugin {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final AgentConfiguration configuration;
    protected final ObjectMapper objectMapper;
    protected final CloseableHttpClient httpClient;
    
    protected BaseApiPlugin(AgentConfiguration configuration) {
        this.configuration = configuration;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.createDefault();
    }
    
    /**
     * Gets the base URL for this API plugin.
     * 
     * @return the base URL for the API
     */
    protected abstract String getBaseUrl();
    
    /**
     * Gets the plugin name for logging and error handling.
     * 
     * @return the plugin name
     */
    protected abstract String getPluginName();
    
    /**
     * Performs a GET request to the specified endpoint.
     * 
     * @param endpoint the API endpoint (relative to base URL)
     * @return the response body as a string
     * @throws PluginException if the request fails
     */
    protected String performGet(String endpoint) throws PluginException {
        return performGet(endpoint, null);
    }
    
    /**
     * Performs a GET request to the specified endpoint with query parameters.
     * 
     * @param endpoint the API endpoint (relative to base URL)
     * @param queryParams query parameters to append (can be null)
     * @return the response body as a string
     * @throws PluginException if the request fails
     */
    protected String performGet(String endpoint, String queryParams) throws PluginException {
        try {
            String url = buildUrl(endpoint, queryParams);
            HttpGet request = new HttpGet(url);
            addCommonHeaders(request);
            
            logger.debug("Performing GET request to: {}", url);
            
            return httpClient.execute(request, response -> {
                int statusCode = response.getCode();
                String responseBody = new String(response.getEntity().getContent().readAllBytes());
                
                if (statusCode >= 200 && statusCode < 300) {
                    logger.debug("GET request successful. Status: {}", statusCode);
                    return responseBody;
                } else {
                    throw new RuntimeException(new PluginException(getPluginName(), "GET " + endpoint, 
                        String.format("HTTP %d: %s", statusCode, responseBody)));
                }
            });
            
        } catch (IOException e) {
            throw new PluginException(getPluginName(), "GET " + endpoint, "IO error", e);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof PluginException) {
                throw (PluginException) e.getCause();
            }
            throw new PluginException(getPluginName(), "GET " + endpoint, "Unexpected error", e);
        } catch (Exception e) {
            throw new PluginException(getPluginName(), "GET " + endpoint, "Unexpected error", e);
        }
    }
    
    /**
     * Performs a POST request to the specified endpoint with JSON body.
     * 
     * @param endpoint the API endpoint (relative to base URL)
     * @param jsonBody the JSON request body
     * @return the response body as a string
     * @throws PluginException if the request fails
     */
    protected String performPost(String endpoint, String jsonBody) throws PluginException {
        try {
            String url = buildUrl(endpoint, null);
            HttpPost request = new HttpPost(url);
            addCommonHeaders(request);
            
            if (jsonBody != null) {
                request.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
            }
            
            logger.debug("Performing POST request to: {}", url);
            
            return httpClient.execute(request, response -> {
                int statusCode = response.getCode();
                String responseBody = new String(response.getEntity().getContent().readAllBytes());
                
                if (statusCode >= 200 && statusCode < 300) {
                    logger.debug("POST request successful. Status: {}", statusCode);
                    return responseBody;
                } else {
                    throw new RuntimeException(new PluginException(getPluginName(), "POST " + endpoint, 
                        String.format("HTTP %d: %s", statusCode, responseBody)));
                }
            });
            
        } catch (IOException e) {
            throw new PluginException(getPluginName(), "POST " + endpoint, "IO error", e);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof PluginException) {
                throw (PluginException) e.getCause();
            }
            throw new PluginException(getPluginName(), "POST " + endpoint, "Unexpected error", e);
        } catch (Exception e) {
            throw new PluginException(getPluginName(), "POST " + endpoint, "Unexpected error", e);
        }
    }
    
    /**
     * Performs a PUT request to the specified endpoint with JSON body.
     * 
     * @param endpoint the API endpoint (relative to base URL)
     * @param jsonBody the JSON request body
     * @return the response body as a string
     * @throws PluginException if the request fails
     */
    protected String performPut(String endpoint, String jsonBody) throws PluginException {
        try {
            String url = buildUrl(endpoint, null);
            HttpPut request = new HttpPut(url);
            addCommonHeaders(request);
            
            if (jsonBody != null) {
                request.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
            }
            
            logger.debug("Performing PUT request to: {}", url);
            
            return httpClient.execute(request, response -> {
                int statusCode = response.getCode();
                String responseBody = new String(response.getEntity().getContent().readAllBytes());
                
                if (statusCode >= 200 && statusCode < 300) {
                    logger.debug("PUT request successful. Status: {}", statusCode);
                    return responseBody;
                } else {
                    throw new RuntimeException(new PluginException(getPluginName(), "PUT " + endpoint, 
                        String.format("HTTP %d: %s", statusCode, responseBody)));
                }
            });
            
        } catch (IOException e) {
            throw new PluginException(getPluginName(), "PUT " + endpoint, "IO error", e);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof PluginException) {
                throw (PluginException) e.getCause();
            }
            throw new PluginException(getPluginName(), "PUT " + endpoint, "Unexpected error", e);
        } catch (Exception e) {
            throw new PluginException(getPluginName(), "PUT " + endpoint, "Unexpected error", e);
        }
    }
    
    /**
     * Performs a DELETE request to the specified endpoint.
     * 
     * @param endpoint the API endpoint (relative to base URL)
     * @return the response body as a string
     * @throws PluginException if the request fails
     */
    protected String performDelete(String endpoint) throws PluginException {
        try {
            String url = buildUrl(endpoint, null);
            HttpDelete request = new HttpDelete(url);
            addCommonHeaders(request);
            
            logger.debug("Performing DELETE request to: {}", url);
            
            return httpClient.execute(request, response -> {
                int statusCode = response.getCode();
                String responseBody = new String(response.getEntity().getContent().readAllBytes());
                
                if (statusCode >= 200 && statusCode < 300) {
                    logger.debug("DELETE request successful. Status: {}", statusCode);
                    return responseBody;
                } else {
                    throw new RuntimeException(new PluginException(getPluginName(), "DELETE " + endpoint, 
                        String.format("HTTP %d: %s", statusCode, responseBody)));
                }
            });
            
        } catch (IOException e) {
            throw new PluginException(getPluginName(), "DELETE " + endpoint, "IO error", e);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof PluginException) {
                throw (PluginException) e.getCause();
            }
            throw new PluginException(getPluginName(), "DELETE " + endpoint, "Unexpected error", e);
        } catch (Exception e) {
            throw new PluginException(getPluginName(), "DELETE " + endpoint, "Unexpected error", e);
        }
    }
    
    private String buildUrl(String endpoint, String queryParams) {
        String url = getBaseUrl();
        if (!url.endsWith("/") && !endpoint.startsWith("/")) {
            url += "/";
        }
        url += endpoint;
        
        if (queryParams != null && !queryParams.isEmpty()) {
            url += "?" + queryParams;
        }
        
        return url;
    }
    
    private void addCommonHeaders(HttpUriRequestBase request) {
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("User-Agent", "BICS-Semantic-Kernel-Agent/1.0.0");
        
        // Add authentication headers here when needed
        // For example: request.setHeader("Authorization", "Bearer " + token);
    }
}