package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Produto;
import com.br.curso.repository.ProdutoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	
	public Produto salvar(Produto cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Produto> listar(){
		return repository.findAll();
	}
	
	public Produto buscar(Integer id) {
		Optional<Produto> prod = repository.findById(id);
		if(prod.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return prod.get();
	}
	public Produto atualizar(Produto cat) {
		return repository.save(cat);
	}
}
