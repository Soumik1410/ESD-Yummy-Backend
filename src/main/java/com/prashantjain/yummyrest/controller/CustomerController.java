package com.prashantjain.yummyrest.controller;

import com.prashantjain.yummyrest.dto.CustomerLoginRequest;
import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid CustomerLoginRequest request) {
        boolean response = customerService.loginCustomer(request);
        if(response)
            return ResponseEntity.ok("Login successful");
        else
            return ResponseEntity.badRequest().body("Login failed");
    }
}
