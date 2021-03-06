package com.br.curso.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cidade;
import com.br.curso.domain.Cliente;
import com.br.curso.domain.Endereco;
import com.br.curso.domain.Estado;
import com.br.curso.domain.ItemPedido;
import com.br.curso.domain.Pagamento;
import com.br.curso.domain.PagamentoComBoleto;
import com.br.curso.domain.PagamentoComCartao;
import com.br.curso.domain.Pedido;
import com.br.curso.domain.Produto;
import com.br.curso.domain.enuns.EstadoPagamento;
import com.br.curso.domain.enuns.Perfil;
import com.br.curso.domain.enuns.TipoCliente;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.repository.CidadeRepository;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.repository.EnderecoRepository;
import com.br.curso.repository.EstadoRepository;
import com.br.curso.repository.ItemPedidoRepository;
import com.br.curso.repository.PagamentoRepository;
import com.br.curso.repository.PedidoRepository;
import com.br.curso.repository.ProdutoRepository;
@Service
public class DBservice {
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
	@Autowired
	private PedidoRepository pedido;
	@Autowired
	private PagamentoRepository pagamento;
	@Autowired
	private ItemPedidoRepository itemPedido;
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instantiateDataBase() throws ParseException {
		Categoria catg1 = new Categoria("informática");
		Categoria catg2 = new Categoria("escritório");
		Categoria catg3 = new Categoria("perfumaria");
		Categoria catg4 = new Categoria("cama mesa e banho");
		Categoria catg5 = new Categoria("decoração");
		Categoria catg6 = new Categoria("eletrônicos");
		Categoria catg7 = new Categoria("jardinagem");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("mouse", 80.00);
		Produto p4 = new Produto("mesa de escritorio", 300.00);
		Produto p5 = new Produto("toalha", 50.00);
		Produto p6 = new Produto("colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("roçadeira", 800.00);
		Produto p9 = new Produto("abajour", 100.00);
		Produto p10 = new Produto("pendente", 100.00);
		Produto p11 = new Produto("shampoo", 90.00);

		catg1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		catg2.getProdutos().addAll(Arrays.asList(p2, p4));
		catg3.getProdutos().addAll(Arrays.asList(p5, p6));
		catg4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		catg5.getProdutos().addAll(Arrays.asList(p8));
		catg6.getProdutos().addAll(Arrays.asList(p9, p10));
		catg7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(catg1, catg4));
		p2.getCategorias().addAll(Arrays.asList(catg1, catg2, catg4));
		p3.getCategorias().addAll(Arrays.asList(catg1, catg4));
		p4.getCategorias().addAll(Arrays.asList(catg2));
		p5.getCategorias().addAll(Arrays.asList(catg3));
		p6.getCategorias().addAll(Arrays.asList(catg3));
		p7.getCategorias().addAll(Arrays.asList(catg4));
		p8.getCategorias().addAll(Arrays.asList(catg5));
		p9.getCategorias().addAll(Arrays.asList(catg6));
		p10.getCategorias().addAll(Arrays.asList(catg6));
		p11.getCategorias().addAll(Arrays.asList(catg7));
		
		Produto p12 = new Produto("Produto 12", 10.00);
		Produto p13 = new Produto("Produto 13", 10.00);
		Produto p14 = new Produto("Produto 14", 10.00);
		Produto p15 = new Produto("Produto 15", 10.00);
		Produto p16 = new Produto("Produto 16", 10.00);
		Produto p17 = new Produto("Produto 17", 10.00);
		Produto p18 = new Produto( "Produto 18", 10.00);
		Produto p19 = new Produto("Produto 19", 10.00);
		Produto p20 = new Produto("Produto 20", 10.00);
		Produto p21 = new Produto("Produto 21", 10.00);
		Produto p22 = new Produto("Produto 22", 10.00);
		Produto p23 = new Produto("Produto 23", 10.00);
		Produto p24 = new Produto("Produto 24", 10.00);
		Produto p25 = new Produto("Produto 25", 10.00);
		Produto p26 = new Produto("Produto 26", 10.00);
		Produto p27 = new Produto("Produto 27", 10.00);
		Produto p28 = new Produto("Produto 28", 10.00);
		Produto p29 = new Produto("Produto 29", 10.00);
		Produto p30 = new Produto("Produto 30", 10.00);
		Produto p31 = new Produto("Produto 31", 10.00);
		Produto p32 = new Produto("Produto 32", 10.00);
		Produto p33 = new Produto("Produto 33", 10.00);
		Produto p34 = new Produto("Produto 34", 10.00);
		Produto p35 = new Produto("Produto 35", 10.00);
		Produto p36 = new Produto("Produto 36", 10.00);
		Produto p37 = new Produto("Produto 37", 10.00);
		Produto p38 = new Produto("Produto 38", 10.00);
		Produto p39 = new Produto("Produto 39", 10.00);
		Produto p40 = new Produto("Produto 40", 10.00);
		Produto p41 = new Produto( "Produto 41", 10.00);
		Produto p42 = new Produto("Produto 42", 10.00);
		Produto p43 = new Produto("Produto 43", 10.00);
		Produto p44 = new Produto("Produto 44", 10.00);
		Produto p45 = new Produto("Produto 45", 10.00);
		Produto p46 = new Produto( "Produto 46", 10.00);
		Produto p47 = new Produto("Produto 47", 10.00);
		Produto p48 = new Produto("Produto 48", 10.00);
		Produto p49 = new Produto("Produto 49", 10.00);
		Produto p50 = new Produto("Produto 50", 10.00);
		catg1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		p12.getCategorias().add(catg1);
		p13.getCategorias().add(catg1);
		p14.getCategorias().add(catg1);
		p15.getCategorias().add(catg1);
		p16.getCategorias().add(catg1);
		p17.getCategorias().add(catg1);
		p18.getCategorias().add(catg1);
		p19.getCategorias().add(catg1);
		p20.getCategorias().add(catg1);
		p21.getCategorias().add(catg1);
		p22.getCategorias().add(catg1);
		p23.getCategorias().add(catg1);
		p24.getCategorias().add(catg1);
		p25.getCategorias().add(catg1);
		p26.getCategorias().add(catg1);
		p27.getCategorias().add(catg1);
		p28.getCategorias().add(catg1);
		p29.getCategorias().add(catg1);
		p30.getCategorias().add(catg1);
		p31.getCategorias().add(catg1);
		p32.getCategorias().add(catg1);
		p33.getCategorias().add(catg1);
		p34.getCategorias().add(catg1);
		p35.getCategorias().add(catg1);
		p36.getCategorias().add(catg1);
		p37.getCategorias().add(catg1);
		p38.getCategorias().add(catg1);
		p39.getCategorias().add(catg1);
		p40.getCategorias().add(catg1);
		p41.getCategorias().add(catg1);
		p42.getCategorias().add(catg1);
		p43.getCategorias().add(catg1);
		p44.getCategorias().add(catg1);
		p45.getCategorias().add(catg1);
		p46.getCategorias().add(catg1);
		p47.getCategorias().add(catg1);
		p48.getCategorias().add(catg1);
		p49.getCategorias().add(catg1);
		p50.getCategorias().add(catg1);

		this.categoria.saveAll(Arrays.asList(catg1, catg2, catg3, catg4, catg5, catg6, catg7));
		this.produto.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		produto.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		Estado est1 = new Estado("Paraíba");
		Estado est2 = new Estado("Pernambuco");

		Cidade cid1 = new Cidade("Monteiro", est1);
		Cidade cid2 = new Cidade("Sertânia", est2);
		Cidade cid3 = new Cidade("Sumé", est1);

		est1.getCidades().addAll(Arrays.asList(cid1, cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));

		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente c1 = new Cliente(null, "Italo", "italo@gmail.com", TipoCliente.PESSOAFISICA,
				"041.5123.511-88", pe.encode("123"));
		
		Cliente c2 = new Cliente(null, "Bil", "contato.italo1020@gmail.com", TipoCliente.PESSOAFISICA,
				"774.508.910-77", pe.encode("12345"));
		c2.addPerfil(Perfil.ADMIN);
		
		c1.getTelefones().addAll(Arrays.asList("83-998354447", "3351-1044"));
		c2.getTelefones().addAll(Arrays.asList("83-57834261"));
		
		Endereco e1 = new Endereco("Rua Maria Ana", "05", "Saida pra Sertânia", "Vila manzuá", "58500-000", c1, cid1);
		Endereco e2 = new Endereco("Roberto de Lima", "25", "Próximo a praça", "Vila popular", "58500-000", c1, cid3);
		Endereco e3 = new Endereco("Roberto de Lima", "25", "Próximo ao lava rápitio", "Vila popular", "58500-000", c2, cid1);

		c1.getEnderecos().addAll(Arrays.asList(e1, e2));
		c2.getEnderecos().addAll(Arrays.asList(e3));
		
		// salva os clientes
		cliente.saveAll(Arrays.asList(c1, c2));
		// slava os endereços
		endereco.saveAll(Arrays.asList(e1, e2, e3));

		// objeto de formatação
		SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sfd.parse("30/09/2017 10:32"), c1, e1);
		Pedido ped2 = new Pedido(null, sfd.parse("10/10/2017 19:35"), c1, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sfd.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pag2);

		c1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		// salva os pagamentos
		pagamento.saveAll(Arrays.asList(pag1, pag2));
		// salva os pedidos
		pedido.saveAll(Arrays.asList(ped1, ped2));

		ItemPedido it1 = new ItemPedido(p1, ped1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(p3, ped1, 0.00, 2, 80);
		ItemPedido it3 = new ItemPedido(p2, ped2, 100.00, 1, 800);

		p1.getItens().addAll(Arrays.asList(it1, it2));
		p2.getItens().addAll(Arrays.asList(it3));

		p1.getItens().addAll(Arrays.asList(it1));
		p2.getItens().addAll(Arrays.asList(it3));
		p3.getItens().addAll(Arrays.asList(it2));

		itemPedido.saveAll(Arrays.asList(it1, it2, it3));
		
	}
}
