package com.unknown.supportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.PersistenceUtil;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistenceUtil persistenceUnitUtil(){
		return Persistence.getPersistenceUtil();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}
