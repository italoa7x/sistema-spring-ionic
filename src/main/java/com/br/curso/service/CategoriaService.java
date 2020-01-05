package com.br.curso.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.repository.CategoriaRepository;

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
		return repository.findById(id).get();
	}
	public Categoria atualizar(Categoria cat) {
		return repository.save(cat);
	}
}
