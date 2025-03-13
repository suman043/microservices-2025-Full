package com.product_Service.service;

import com.product_Service.dto.ProductDTO;
import com.product_Service.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDTO productDTO);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
}

