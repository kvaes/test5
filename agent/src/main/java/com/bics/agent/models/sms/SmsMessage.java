package com.bics.agent.models.sms;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SmsMessage model representing an SMS message entity in the SMS API.
 * This class is based on the OpenAPI specification response examples.
 */
public class SmsMessage {
    
    @JsonProperty("message_id")
    private String messageId;
    
    @JsonProperty("from")
    private String from;
    
    @JsonProperty("to")
    private String to;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("sent_at")
    private String sentAt;
    
    @JsonProperty("delivered_at")
    private String deliveredAt;
    
    @JsonProperty("error_code")
    private String errorCode;
    
    @JsonProperty("error_message")
    private String errorMessage;
    
    // Default constructor
    public SmsMessage() {}
    
    // Constructor with parameters
    public SmsMessage(String messageId, String from, String to, String message, String status) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.message = message;
        this.status = status;
    }
    
    // Getters and setters
    public String getMessageId() {
        return messageId;
    }
    
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
    
    public String getDeliveredAt() {
        return deliveredAt;
    }
    
    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString() {
        return "SmsMessage{" +
                "messageId='" + messageId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", sentAt='" + sentAt + '\'' +
                ", deliveredAt='" + deliveredAt + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}