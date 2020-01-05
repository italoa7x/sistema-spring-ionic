package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	
	public Cliente salvar(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Cliente> listar(){
		return repository.findAll();
	}
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		if(obj.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj.get();
	}
	public Cliente atualizar(Cliente obj) {
		obj = this.buscar(obj.getId());
		if(obj == null) {
			return null;
		}
		return repository.save(obj);
	}
}
