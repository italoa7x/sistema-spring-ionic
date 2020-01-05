package com.br.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.curso.domain.Categoria;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.service.CategoriaService;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoria;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria catg1 = new Categoria("Informática");
		Categoria catg2 = new Categoria("Alimentos");
		Categoria catg3 = new Categoria("Limpeza");
		Categoria catg4 = new Categoria("Marcenaria");
		
		this.categoria.saveAll(Arrays.asList(catg1, catg2, catg3, catg4));
	}

}
