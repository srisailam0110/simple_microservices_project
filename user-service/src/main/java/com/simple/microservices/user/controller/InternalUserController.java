package com.simple.microservices.user.controller;

import com.simple.microservices.user.dto.UserDto;
import com.simple.microservices.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/internal")
public class InternalUserController {

    private final UserService userService;

    public InternalUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserInternal(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserInternal(id));
    }

}
