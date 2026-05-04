package com.simple.microservices.order.controller;
import com.simple.microservices.order.dto.*; import com.simple.microservices.order.service.OrderService; import java.util.List; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
