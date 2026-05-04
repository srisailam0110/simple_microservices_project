package com.simple.microservices.product.controller;

import com.simple.microservices.product.dto.ProductDto;
import com.simple.microservices.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/internal")
public class InternalProductController {

    private final ProductService productService;

    public InternalProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInternal(@PathVariable String id) {
        return ResponseEntity.ok(productService.internal(id));
    }

}
