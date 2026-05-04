package com.simple.microservices.order.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.ArrayList; import java.util.List;

@Entity
@Table(name = "orders")

public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        if(this.status==null)
            this.status="CREATED";
    }

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
    public void setStatus(String status){
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
    public List<OrderItem> getItems(){
        return items;
    }
    public void setItems(List<OrderItem> items){
        this.items=items;
    }
}
