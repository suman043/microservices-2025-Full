package com.auth_Service1.controller;

import com.auth_Service1.client.ProductClient;
import com.auth_Service1.dto.ProductDTO;
import com.auth_Service1.entity.User;
import com.auth_Service1.service.UserService;
import com.auth_Service1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ProductClient productClient;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
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