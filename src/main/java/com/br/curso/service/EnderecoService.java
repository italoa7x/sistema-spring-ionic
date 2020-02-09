package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Endereco;
import com.br.curso.repository.EnderecoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	
	
	public Endereco salvar(Endereco cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Endereco> listar(){
		return repository.findAll();
	}
	
	public Endereco buscar(Integer id) {
		Optional<Endereco> cat = repository.findById(id);
		if(cat.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Endereco.class.getName());
		}
		return cat.get();
	}
	public Endereco atualizar(Endereco obj) {
		obj = this.buscar(obj.getId());
		if(obj == null) {
			return null;
		}
		return repository.save(obj);
	}
}
