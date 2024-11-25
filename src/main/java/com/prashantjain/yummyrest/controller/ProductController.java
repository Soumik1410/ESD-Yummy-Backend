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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable  Long id) {
        ProductResponse obj = productService.deleteProduct(id);
        if(obj != null)
            return ResponseEntity.ok("Deleted customer entry : " + obj.toString());
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequest request) {
        String result = productService.updateProduct(id, request);
        if(result != null)
            //return ResponseEntity.ok("Old details : " + obj.get(0).toString() + "\nNew updated details : " + obj.get(1).toString());
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }
}
