package com.simple.microservices.order.dto;
import java.math.BigDecimal;
public class OrderItemDto {
    private Long id;
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getProductId(){
        return productId;
    }
    public void setProductId(String productId){
        this.productId=productId;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
}