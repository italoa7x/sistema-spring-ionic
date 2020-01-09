package com.br.curso.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.curso.service.DBservice;

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
}
