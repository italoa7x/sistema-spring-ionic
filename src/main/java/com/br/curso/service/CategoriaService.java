package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	
	public Categoria salvar(Categoria cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Categoria> listar(){
		return repository.findAll();
	}
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repository.findById(id);
		if(cat.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return cat.get();
	}
	public Categoria atualizar(Categoria cat) {
		buscar(cat.getId());
		return repository.save(cat);
	}
}
