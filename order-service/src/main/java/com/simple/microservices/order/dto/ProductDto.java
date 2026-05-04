package com.simple.microservices.order.dto;
import java.math.BigDecimal;

public class ProductDto {
    private String id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer availableQuantity;
    private Boolean inStock;
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSku(){
        return sku;
    }
    public void setSku(String sku){
        this.sku=sku;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public Integer getAvailableQuantity(){
        return availableQuantity;
    }
    public void setAvailableQuantity(Integer availableQuantity){
        this.availableQuantity=availableQuantity;
    }
    public Boolean getInStock(){
        return inStock;
    }
    public void setInStock(Boolean inStock){
        this.inStock=inStock;
    }
}