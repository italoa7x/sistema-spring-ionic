package com.br.curso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.curso.service.DBservice;
import com.br.curso.service.EmailService;
import com.br.curso.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBservice dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean inicializeDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		} else {
			dbService.instantiateDataBase();
			return true;
		}

	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
