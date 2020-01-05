package com.br.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cidade;
import com.br.curso.domain.Estado;
import com.br.curso.domain.Produto;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.repository.CidadeRepository;
import com.br.curso.repository.EstadoRepository;
import com.br.curso.repository.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoria;
	@Autowired
	private ProdutoRepository produto;
	@Autowired
	private CidadeRepository cidade;
	@Autowired
	private EstadoRepository estado;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria catg1 = new Categoria("informática");
		Categoria catg2 = new Categoria("escritório");
		
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3  = new Produto("mouse", 80.00);
		
		catg1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		catg2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(catg1));
		p2.getCategorias().addAll(Arrays.asList(catg1, catg2));
		p3.getCategorias().addAll(Arrays.asList(catg1));
				
		this.categoria.saveAll(Arrays.asList(catg1, catg2));
		this.produto.saveAll(Arrays.asList(p1, p2, p3));
		
		Cidade cid1 = new Cidade("Monteiro");
		Cidade cid2 = new Cidade("Sertânia");
		Cidade cid3 = new Cidade("Sumé");
		
		Estado est1 = new Estado("Paraíba");
		Estado est2 = new Estado("Pernambuco");
		
		cid1.setEstados(est1);
		cid2.setEstados(est2);
		cid3.setEstados(est1);
		
		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(cid1, cid2, cid3));
	}
	

}
