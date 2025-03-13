package com.auth_Service.service;

import com.auth_Service.dto.LoginDTO;
import com.auth_Service.dto.UserDTO;
import com.auth_Service.response.JwtAuthResponse;

public interface AuthService {

    String registerUser(UserDTO userDTO);

    JwtAuthResponse loginUser(LoginDTO loginDTO);
}

