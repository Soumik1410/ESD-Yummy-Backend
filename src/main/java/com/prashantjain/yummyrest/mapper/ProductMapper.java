package com.prashantjain.yummyrest.mapper;

import com.prashantjain.yummyrest.dto.ProductRequest;
//import com.prashantjain.yummyrest.dto.ProductResponse;
import com.prashantjain.yummyrest.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

}
