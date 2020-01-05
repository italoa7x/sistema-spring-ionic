package com.br.curso.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Produto;
import com.br.curso.repository.ProdutoRepository;

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
		return repository.findById(id).get();
	}
	public Produto atualizar(Produto cat) {
		return repository.save(cat);
	}
}
