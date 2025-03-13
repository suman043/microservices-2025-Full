package com.auth_Service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthService1Application {

	public static void main(String[] args) {
		SpringApplication.run(AuthService1Application.class, args);
	}

}