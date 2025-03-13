package com.auth_Service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Set<String> roles;
}
