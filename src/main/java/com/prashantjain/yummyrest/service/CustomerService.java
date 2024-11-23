package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.*;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import com.prashantjain.yummyrest.helper.EncryptionService;
import com.prashantjain.yummyrest.helper.JWTHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }

    @Transactional
    public String loginCustomer(CustomerLoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if(customer == null)
            return "Login Failed";
        if(!encryptionService.validates(request.password(), customer.getPassword()))
            return "Login Failed";
        String token = jwtHelper.generateToken(request.email());
        repo.insertAccessToken(request.email(), token);
        return token;
    }

    public CustomerResponse getCustomer(CustomerDetailsRequest request) {
        boolean validity = jwtHelper.validateToken(request.access_token());
        if(!validity)
            return null;
        String email = jwtHelper.extractUsername(request.access_token());
        Customer customer = repo.findByEmail(email);
        return customerMapper.toCustomerResponse(customer);
    }

    @Transactional
    public CustomerResponse deleteCustomer(CustomerDetailsRequest request) {
        boolean validity = jwtHelper.validateToken(request.access_token());
        if(!validity)
            return null;
        String email = jwtHelper.extractUsername(request.access_token());
        Customer customer = repo.findByEmail(email);
        repo.delete(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Transactional
    public String updateCustomer(CustomerDetailsUpdateRequest request) {
        boolean validity = jwtHelper.validateToken(request.access_token());
        if(!validity)
            return null;
        String email = jwtHelper.extractUsername(request.access_token());
        Customer oldCustomer = repo.findByEmail(email);
        repo.flush();
        String first_name = request.firstName(), last_name = request.lastName(), address = request.address(), city = request.city(), pincode = request.pincode();
        if(request.firstName().isEmpty())
            first_name = oldCustomer.getFirstName();
        if(request.lastName().isEmpty())
            last_name = oldCustomer.getLastName();
        if(request.address().isEmpty())
            address = oldCustomer.getAddress();
        if(request.city().isEmpty())
            city = oldCustomer.getCity();
        if(request.pincode().isEmpty())
            pincode = oldCustomer.getPincode();
        repo.updateCustomer(email, first_name, last_name, address, city, pincode);
        return "Updated Successfully";
    }
}
