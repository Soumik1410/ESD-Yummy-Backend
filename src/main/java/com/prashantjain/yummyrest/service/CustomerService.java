package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.CustomerLoginRequest;
import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import com.prashantjain.yummyrest.helper.EncryptionService;
import com.prashantjain.yummyrest.helper.JWTHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer(CustomerLoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if(customer == null)
            return "Login Failed";
        if(!encryptionService.validates(request.password(), customer.getPassword()))
            return "Login Failed";
        return jwtHelper.generateToken(request.email());
    }
}
