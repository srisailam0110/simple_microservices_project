package com.simple.microservices.notification.dto;
import java.time.LocalDateTime;
import java.util.Map;
public class NotificationDto {
    private String id;
    private Long userId;
    private Long orderId;
    private String type;
    private String recipient;
    private String subject;
    private String message;
    private String status;
    private Map<String,Object> metadata;
    private LocalDateTime createdAt;
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId){
        this.orderId=orderId;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getRecipient(){
        return recipient;
    }
    public void setRecipient(String recipient){
        this.recipient=recipient;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public Map<String,Object> getMetadata(){
        return metadata;
    }
    public void setMetadata(Map<String,Object> metadata){
        this.metadata=metadata;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
}