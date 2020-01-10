package com.br.curso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.curso.service.DBservice;
import com.br.curso.service.EmailService;
import com.br.curso.service.MockeEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBservice dbService;
	
	@Bean
	public boolean inicializeDatabase() throws ParseException {
		dbService.instantiateDataBase();
		return true;
	}
	

	@Bean
	public EmailService emailService() {
		return new MockeEmailService();
	}
}
