package com.product_Service.controller;

import com.product_Service.dto.ProductDTO;
import com.product_Service.entity.Product;
import com.product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product") // ✅ Base path for all endpoints
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add") // ✅ Matches Feign Client
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/all") // ✅ Matches Feign Client
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}") // ✅ Matches Feign Client
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}") // ✅ Deleting a product
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}