package com.auth_Service1.client;

import com.auth_Service1.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE") // ✅ Service registered in Eureka
public interface ProductClient {

    @GetMapping("/product/all") // ✅ Matches Product Service
    ResponseEntity<List<Object>> getAllProducts();

    @GetMapping("/product/{id}") // ✅ Matches Product Service
    ResponseEntity<Object> getProductById(@PathVariable Long id);

    @PostMapping("/product/add") // ✅ Matches Product Service
    ResponseEntity<Object> addProduct(@RequestBody ProductDTO productRequest);
}