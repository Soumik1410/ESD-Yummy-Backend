package com.prashantjain.yummyrest.mapper;

import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.dto.ProductRequest;
import com.prashantjain.yummyrest.dto.ProductResponse;
import com.prashantjain.yummyrest.entity.Customer;
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

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
