package com.br.curso.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.curso.domain.ItemPedido;
import com.br.curso.domain.PagamentoComBoleto;
import com.br.curso.domain.Pedido;
import com.br.curso.domain.enuns.EstadoPagamento;
import com.br.curso.domain.enuns.TipoCliente;
import com.br.curso.repository.ItemPedidoRepository;
import com.br.curso.repository.PagamentoRepository;
import com.br.curso.repository.PedidoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	
	@Transactional
	public Pedido salvar(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Collection<Pedido> listar() {
		return repository.findAll();
	}

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		if (obj.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj.get();
	}

	public Pedido atualizar(Pedido obj) {
		obj = this.buscar(obj.getId());
		if (obj == null) {
			return null;
		}
		return repository.save(obj);
	}
}
