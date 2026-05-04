package com.simple.microservices.order.client;
import com.simple.microservices.order.dto.UserDto; import org.springframework.cloud.openfeign.FeignClient; import org.springframework.web.bind.annotation.*;
@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/api/users/internal/{id}")
    UserDto getUser(@PathVariable("id") Long id);
}
