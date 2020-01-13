package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cidade;
import com.br.curso.repository.CidadeRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository repository;

	public Cidade salvar(Cidade obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Collection<Cidade> listar() {
		return repository.findAll();
	}

	public Cidade buscar(Integer id) {
		Optional<Cidade> cat = repository.findById(id);
		if (cat.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Cidade.class.getName());
		}
		return cat.get();
	}

	public Cidade atualizar(Cidade cat) {
		cat = this.buscar(cat.getId());
		if (cat == null) {
			return null;
		}
		return repository.save(cat);
	}

}
