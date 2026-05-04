package com.simple.microservices.notification.dto;
import java.math.BigDecimal;
public class OrderCreatedEvent {
    private Long orderId;
    private Long userId;
    private String username;
    private String email;
    private BigDecimal totalAmount;
    private String status;

    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId){
        this.orderId=orderId;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount=totalAmount;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
}