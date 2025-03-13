package com.auth_Service.controller;

import com.auth_Service.client.ProductClient;
import com.auth_Service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // ✅ Matches API Gateway path
public class AuthProductController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/product/all") // ✅ Matches Feign Client
    public ResponseEntity<List<Object>> getAllProducts() {
        return productClient.getAllProducts();
    }

    @GetMapping("/product/{id}") // ✅ Matches Feign Client
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return productClient.getProductById(id);
    }

    @PostMapping("/product/add") // ✅ Matches Feign Client
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productRequest) {
        return productClient.addProduct(productRequest);
    }
}