package com.simple.microservices.order.client;
import com.simple.microservices.order.dto.ProductDto; import org.springframework.cloud.openfeign.FeignClient; import org.springframework.web.bind.annotation.*;
@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @GetMapping("/api/products/internal/{id}")
    ProductDto getProduct(@PathVariable("id") String id);
}
