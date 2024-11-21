package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.CustomerLoginRequest;
import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public boolean loginCustomer(CustomerLoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if(customer == null)
            return false;
        return request.password().equals(customer.getPassword());
    }
}
