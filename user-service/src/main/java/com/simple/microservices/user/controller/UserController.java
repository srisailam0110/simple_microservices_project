package com.simple.microservices.user.controller;

import com.simple.microservices.user.dto.UserAddressDto;
import com.simple.microservices.user.dto.UserDto;
import com.simple.microservices.user.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<List<UserAddressDto>> getAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAddressesByUserId(userId));
    }
    @PostMapping("/addresses")
    public ResponseEntity<UserAddressDto> createAddress(@RequestBody UserAddressDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAddress(dto));
    }
    @PutMapping("/addresses/{id}")
    public ResponseEntity<UserAddressDto> updateAddress(@PathVariable Long id, @RequestBody UserAddressDto dto) {
        return ResponseEntity.ok(userService.updateAddress(id, dto));
    }
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        userService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
