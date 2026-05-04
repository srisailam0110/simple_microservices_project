package com.simple.microservices.user.service;

import com.simple.microservices.user.dto.*;
import com.simple.microservices.user.entity.User;
import com.simple.microservices.user.entity.UserAddress;
import com.simple.microservices.user.exception.BadRequestException;
import com.simple.microservices.user.exception.ResourceNotFoundException;
import com.simple.microservices.user.repository.UserAddressRepository;
import com.simple.microservices.user.repository.UserRepository;
import com.simple.microservices.user.security.JwtUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserAddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository, UserAddressRepository addressRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository; this.addressRepository = addressRepository; this.passwordEncoder = passwordEncoder; this.jwtUtil = jwtUtil;
    }
    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new BadRequestException("Username already exists");
        if (userRepository.existsByEmail(request.getEmail())) throw new BadRequestException("Email already exists");
        User user = new User();
        user.setFullName(request.getFullName()); user.setUsername(request.getUsername()); user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); user.setPhone(request.getPhone()); user.setRole("CUSTOMER");
        return map(userRepository.save(user));
    }
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new BadRequestException("Invalid credentials");
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token, user.getId(), user.getUsername(), user.getRole());
    }
    public List<UserDto> getAllUsers() { return userRepository.findAll().stream().map(this::map).collect(Collectors.toList()); }
    public UserDto getUserById(Long id) { return map(getUserEntity(id)); }
    public UserDto updateUser(Long id, UserDto dto) {
        User user = getUserEntity(id);
        user.setFullName(dto.getFullName()); user.setPhone(dto.getPhone()); user.setRole(dto.getRole()); user.setActive(dto.isActive());
        return map(userRepository.save(user));
    }
    public void deleteUser(Long id) { userRepository.delete(getUserEntity(id)); }
    public List<UserAddressDto> getAddressesByUserId(Long userId) { getUserEntity(userId); return addressRepository.findByUserId(userId).stream().map(this::mapAddress).collect(Collectors.toList()); }
    public UserAddressDto createAddress(UserAddressDto dto) { getUserEntity(dto.getUserId()); UserAddress address = new UserAddress(); applyAddress(address, dto); return mapAddress(addressRepository.save(address)); }
    public UserAddressDto updateAddress(Long id, UserAddressDto dto) { UserAddress address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found")); applyAddress(address, dto); return mapAddress(addressRepository.save(address)); }
    public void deleteAddress(Long id) { addressRepository.delete(addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found"))); }
    public UserDto getUserInternal(Long id) { return map(getUserEntity(id)); }
    private User getUserEntity(Long id) { return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); }
    private UserDto map(User user) { UserDto dto = new UserDto(); dto.setId(user.getId()); dto.setFullName(user.getFullName()); dto.setUsername(user.getUsername()); dto.setEmail(user.getEmail()); dto.setPhone(user.getPhone()); dto.setRole(user.getRole()); dto.setActive(user.isActive()); return dto; }
    private void applyAddress(UserAddress address, UserAddressDto dto) { address.setUserId(dto.getUserId()); address.setAddressLine1(dto.getAddressLine1()); address.setAddressLine2(dto.getAddressLine2()); address.setCity(dto.getCity()); address.setState(dto.getState()); address.setCountry(dto.getCountry()); address.setZipCode(dto.getZipCode()); address.setPrimaryAddress(dto.isPrimaryAddress()); }
    private UserAddressDto mapAddress(UserAddress address) { UserAddressDto dto = new UserAddressDto(); dto.setId(address.getId()); dto.setUserId(address.getUserId()); dto.setAddressLine1(address.getAddressLine1()); dto.setAddressLine2(address.getAddressLine2()); dto.setCity(address.getCity()); dto.setState(address.getState()); dto.setCountry(address.getCountry()); dto.setZipCode(address.getZipCode()); dto.setPrimaryAddress(address.isPrimaryAddress()); return dto; }
}
