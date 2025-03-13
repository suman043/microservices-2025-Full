package com.auth_Service.controller;

import com.auth_Service.client.ProductClient;
import com.auth_Service.dto.LoginDTO;
import com.auth_Service.dto.ProductDTO;
import com.auth_Service.dto.UserDTO;
import com.auth_Service.response.JwtAuthResponse;
import com.auth_Service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ProductClient productClient;

    @PostMapping("/open/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/open/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponse response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<Object>> getAllProducts() {
        return productClient.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return productClient.getProductById(id);
    }

    @PostMapping("/product/add")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productRequest) {
        return productClient.addProduct(productRequest);
    }

    @GetMapping("/testing")
    public String getMessage(){
        return "Successful Api";
    }
}