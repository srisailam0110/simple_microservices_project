package com.simple.microservices.product.controller;

import com.simple.microservices.product.dto.ProductDto;
import com.simple.microservices.product.service.ProductService;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.*; import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
    	dto.setId(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable String id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }
    @PutMapping("/{id}/inventory")
    public ResponseEntity<ProductDto> updateInventory(@PathVariable String id, @RequestBody Map<String, Integer> request) {
        return ResponseEntity.ok(productService.updateInventory(id, request.get("availableQuantity")));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
