package com.simple.microservices.product.config;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/api/products/internal/**","/actuator/**").permitAll().anyRequest().permitAll());
        return http.build();
    }

}
