package com.auth_Service.serviceImpl;

import com.auth_Service.dto.LoginDTO;
import com.auth_Service.dto.UserDTO;
import com.auth_Service.entity.Role;
import com.auth_Service.entity.User;
import com.auth_Service.repo.RoleRepository;
import com.auth_Service.repo.UserRepository;
import com.auth_Service.response.JwtAuthResponse;
import com.auth_Service.service.AuthService;
import com.auth_Service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public String registerUser(UserDTO userDTO) {
//        Role role = roleRepository.findByName("name")
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        User user = User.builder()
//                .firstName(userDTO.getFirstName())
//                .lastName(userDTO.getLastName())
//                .email(userDTO.getEmail())
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .roles(Collections.singleton(role))
//                .build();
//
//        userRepository.save(user);
//        return "User Registered Successfully";
//    }

//      @Override
//    public String registerUser(UserDTO userDTO) {
//        User user = new User();
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//
//        Set<Role> roles = new HashSet<>();
//        for (String roleName : userDTO.getRoles()) {
//            Role role = roleRepository.findByName(roleName)
//                    .orElseThrow(() -> new RuntimeException("❌ Role not found: " + roleName));
//            roles.add(role);
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return "User registered successfully";
//    }

    @Override
    public String registerUser(UserDTO userDTO) {
        System.out.println("Roles received: " + userDTO.getRoles()); // Debugging

        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            throw new RuntimeException("❌ No roles provided in request!");
        }

        Set<Role> roles = new HashSet<>();
        for (String roleName : userDTO.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("❌ Role not found: " + roleName));
            roles.add(role);
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully";
    }



    @Override
    public JwtAuthResponse loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtAuthResponse(token);
    }
}