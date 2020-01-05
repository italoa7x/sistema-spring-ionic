package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Estado;
import com.br.curso.repository.EstadoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository repository;
	
	
	public Estado salvar(Estado cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Estado> listar(){
		return repository.findAll();
	}
	
	public Estado buscar(Integer id) {
		Optional<Estado> cat = repository.findById(id);
		if(cat.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Estado.class.getName());
		}
		return cat.get();
	}
	public Estado atualizar(Estado es) {
		es = this.buscar(es.getId());
		if(es == null) {
			return null;
		}
		return repository.save(es);
	}
}
