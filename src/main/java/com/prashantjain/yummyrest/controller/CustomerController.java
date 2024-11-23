package com.prashantjain.yummyrest.controller;

import com.prashantjain.yummyrest.dto.CustomerDetailsRequest;
import com.prashantjain.yummyrest.dto.CustomerLoginRequest;
import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String response = customerService.loginCustomer(request);
        if(!response.equals("Login Failed"))
            return ResponseEntity.ok("Login successful\nJWT Token: " + response);
        else
            return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/details")
    public ResponseEntity<String> getCustomer(@RequestBody @Valid CustomerDetailsRequest request) {
        CustomerResponse obj = customerService.getCustomer(request);
        if(obj != null)
            return ResponseEntity.ok(obj.toString());
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestBody @Valid CustomerDetailsRequest request) {
        CustomerResponse obj = customerService.deleteCustomer(request);
        if(obj != null)
            return ResponseEntity.ok("Deleted customer entry : " + obj.toString());
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }
}
