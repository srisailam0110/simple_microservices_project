package com.simple.microservices.order.repository;
import com.simple.microservices.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}