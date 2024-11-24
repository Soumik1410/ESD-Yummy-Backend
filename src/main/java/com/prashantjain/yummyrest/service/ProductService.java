package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.*;
import com.prashantjain.yummyrest.entity.Product;
import com.prashantjain.yummyrest.mapper.ProductMapper;
import com.prashantjain.yummyrest.repo.ProductRepo;
import com.prashantjain.yummyrest.helper.EncryptionService;
import com.prashantjain.yummyrest.helper.JWTHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Created";
    }

}
