package com.br.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cidade;
import com.br.curso.domain.Cliente;
import com.br.curso.domain.Endereco;
import com.br.curso.domain.Estado;
import com.br.curso.domain.Produto;
import com.br.curso.domain.enuns.TipoCliente;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.repository.CidadeRepository;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.repository.EnderecoRepository;
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
	@Autowired
	private EnderecoRepository endereco;
	@Autowired
	private ClienteRepository cliente;
	
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
		
		Estado est1 = new Estado("Paraíba");
		Estado est2 = new Estado("Pernambuco");
		
		Cidade cid1 = new Cidade("Monteiro", est1);
		Cidade cid2 = new Cidade("Sertânia", est2);
		Cidade cid3 = new Cidade("Sumé", est1);

		est1.getCidades().addAll(Arrays.asList(cid1, cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente c1 = new Cliente(null, "Italo", "italo@gmail.com", TipoCliente.PESSOAFISICA);
		c1.getTelefone().addAll(Arrays.asList("83-998354447","3351-1044"));
		Endereco e1 = new Endereco("Rua Maria Ana", "05", "Saida pra Sertânia", "Vila manzuá", "58500-000", c1, cid1);
		Endereco e2 = new Endereco("Roberto de Lima", "25", "Próximo a praça", "Vila popular", "58500-000", c1, cid3);
		
		c1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cliente.saveAll(Arrays.asList(c1));
		endereco.saveAll(Arrays.asList(e1, e2));
		
	}
	

}
