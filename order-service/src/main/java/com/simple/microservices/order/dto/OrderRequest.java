package com.simple.microservices.order.dto;
import java.util.List;

public class OrderRequest {
    private Long userId;
    private String shippingAddress;
    private String status;
    private List<OrderItemRequest> items;

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public String getShippingAddress(){
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress){
        this.shippingAddress=shippingAddress;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public List<OrderItemRequest> getItems(){
        return items;
    }
    public void setItems(List<OrderItemRequest> items){
        this.items=items;
    }
}