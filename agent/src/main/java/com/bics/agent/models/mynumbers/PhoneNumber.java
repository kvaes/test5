package com.bics.agent.models.mynumbers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PhoneNumber model representing a phone number entity in the MyNumbers API.
 * This class is based on the OpenAPI specification response examples.
 */
public class PhoneNumber {
    
    @JsonProperty("number")
    private String number;
    
    @JsonProperty("country_code")
    private String countryCode;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("assigned_customer_id")
    private String assignedCustomerId;
    
    @JsonProperty("activation_date")
    private String activationDate;
    
    @JsonProperty("expiry_date")
    private String expiryDate;
    
    @JsonProperty("features")
    private String[] features;
    
    // Default constructor
    public PhoneNumber() {}
    
    // Constructor with parameters
    public PhoneNumber(String number, String countryCode, String status, String type) {
        this.number = number;
        this.countryCode = countryCode;
        this.status = status;
        this.type = type;
    }
    
    // Getters and setters
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getAssignedCustomerId() {
        return assignedCustomerId;
    }
    
    public void setAssignedCustomerId(String assignedCustomerId) {
        this.assignedCustomerId = assignedCustomerId;
    }
    
    public String getActivationDate() {
        return activationDate;
    }
    
    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }
    
    public String getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String[] getFeatures() {
        return features;
    }
    
    public void setFeatures(String[] features) {
        this.features = features;
    }
    
    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", assignedCustomerId='" + assignedCustomerId + '\'' +
                ", activationDate='" + activationDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", features=" + java.util.Arrays.toString(features) +
                '}';
    }
}