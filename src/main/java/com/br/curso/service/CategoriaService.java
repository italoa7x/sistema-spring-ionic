package com.br.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.service.exception.DataIntegratyException;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	
	public Categoria salvar(Categoria cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public void excluir(Integer id) {
		buscar(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegratyException("Não é possível excluir uma categoria que possui produtos.");
		}
	}
	
	public List<Categoria> listar(){
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
