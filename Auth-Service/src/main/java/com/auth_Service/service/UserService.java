package com.auth_Service.service;

import com.auth_Service.dto.UserDTO;
import com.auth_Service.entity.User;

public interface UserService {
    User saveUser(UserDTO userDTO);
    User findByEmail(String email);
}