package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Pedido;
import com.br.curso.repository.PedidoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	
	public Pedido salvar(Pedido obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Pedido> listar(){
		return repository.findAll();
	}
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		if(obj.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj.get();
	}
	public Pedido atualizar(Pedido obj) {
		obj = this.buscar(obj.getId());
		if(obj == null) {
			return null;
		}
		return repository.save(obj);
	}
}
