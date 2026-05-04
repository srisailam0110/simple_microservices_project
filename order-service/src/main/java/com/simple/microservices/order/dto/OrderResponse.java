package com.simple.microservices.order.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status=status;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount=totalAmount;
    }
    public String getShippingAddress(){
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress){
        this.shippingAddress=shippingAddress;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public List<OrderItemDto> getItems(){
        return items;
    }
    public void setItems(List<OrderItemDto> items){
        this.items=items;
    }
}