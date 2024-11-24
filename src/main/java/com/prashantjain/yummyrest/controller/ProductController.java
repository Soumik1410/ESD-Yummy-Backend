package com.prashantjain.yummyrest.controller;

import com.prashantjain.yummyrest.dto.*;
import com.prashantjain.yummyrest.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(@PathVariable  Long id) {
        ProductResponse obj = productService.getProduct(id);
        if(obj != null)
            return ResponseEntity.ok(obj.toString());
        else
            return ResponseEntity.badRequest().body("Invalid product id passed in request");
    }
}
