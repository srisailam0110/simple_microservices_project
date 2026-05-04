package com.simple.microservices.order.service;

import com.simple.microservices.order.client.ProductFeignClient;
import com.simple.microservices.order.client.UserFeignClient;
import com.simple.microservices.order.dto.*;
import com.simple.microservices.order.entity.CustomerOrder;
import com.simple.microservices.order.entity.OrderItem;
import com.simple.microservices.order.exception.BadRequestException;
import com.simple.microservices.order.exception.ResourceNotFoundException;
import com.simple.microservices.order.repository.CustomerOrderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerOrderRepository orderRepository;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    @Value("${app.kafka.order-created-topic}")
    private String topic;
    public OrderService(CustomerOrderRepository orderRepository, UserFeignClient userFeignClient, ProductFeignClient productFeignClient, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository; this.userFeignClient = userFeignClient; this.productFeignClient = productFeignClient; this.kafkaTemplate = kafkaTemplate;
    }
    public OrderResponse create(OrderRequest request) {
        UserDto user = userFeignClient.getUser(request.getUserId());
        if (request.getItems() == null || request.getItems().isEmpty()) throw new BadRequestException("Order items cannot be empty");
        CustomerOrder order = new CustomerOrder();
        order.setUserId(user.getId()); order.setShippingAddress(request.getShippingAddress()); order.setStatus(request.getStatus() == null ? "CREATED" : request.getStatus());
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest itemRequest : request.getItems()) {
            ProductDto product = productFeignClient.getProduct(itemRequest.getProductId());
            if (product.getAvailableQuantity() == null || product.getAvailableQuantity() < itemRequest.getQuantity()) throw new BadRequestException("Insufficient stock for product: " + product.getName());
            OrderItem item = new OrderItem();
            item.setOrder(order); item.setProductId(product.getId()); item.setProductName(product.getName()); item.setQuantity(itemRequest.getQuantity()); item.setPrice(product.getPrice());
            order.getItems().add(item);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }
        order.setTotalAmount(total);
        CustomerOrder saved = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(saved.getId()); event.setUserId(user.getId()); event.setUsername(user.getUsername()); event.setEmail(user.getEmail()); event.setTotalAmount(saved.getTotalAmount()); event.setStatus(saved.getStatus());
        kafkaTemplate.send(topic, String.valueOf(saved.getId()), event);
        return map(saved);
    }
    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }
    public OrderResponse getById(Long id) {
        return map(getEntity(id));
    }
    public OrderResponse update(Long id, OrderRequest request) {
        CustomerOrder order = getEntity(id); order.setStatus(request.getStatus() != null ? request.getStatus() : order.getStatus()); order.setShippingAddress(request.getShippingAddress()); return map(orderRepository.save(order));
    }
    public void delete(Long id) {
        orderRepository.delete(getEntity(id));
    }
    private CustomerOrder getEntity(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    private OrderResponse map(CustomerOrder order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setStatus(order.getStatus());
        response.setShippingAddress(order.getShippingAddress());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setItems(order.getItems().stream().map(item -> { OrderItemDto dto = new OrderItemDto();
            dto.setId(item.getId());
            dto.setProductId(item.getProductId());
            dto.setProductName(item.getProductName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            return dto;
        }).collect(Collectors.toList()));

        return response;

    }
}
