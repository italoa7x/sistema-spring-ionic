package com.br.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.dto.CategoriaDTO;
import com.br.curso.repository.CategoriaRepository;
import com.br.curso.service.exception.DataIntegratyException;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getNome());
	}

	public Categoria salvar(Categoria cat) {
		cat.setId(null);
		return repository.save(cat);
	}

	public void excluir(Integer id) {
		buscar(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegratyException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repository.findById(id);
		if (cat.isPresent() == false) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return cat.get();
	}

	public Categoria atualizar(Categoria cat) {
		buscar(cat.getId());
		return repository.save(cat);
	}

	public List<Categoria> listar() {
		return repository.findAll();
	}

	public Page<Categoria> listarPaginacao(Integer page, Integer linesPerPage, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repository.findAll(pageRequest);
	}
}
