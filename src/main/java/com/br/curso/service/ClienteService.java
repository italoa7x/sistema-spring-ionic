package com.br.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cliente;
import com.br.curso.dto.ClienteDTO;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente salvar(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public void excluir(Integer id) {
		repository.deleteById(id);
	}

	public List<Cliente> listar() {
		return repository.findAll();
	}

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		if (obj.isPresent() == false) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.get();
	}

	public Cliente atualizar(Cliente obj) {
		Cliente novoCli = this.buscar(obj.getId());
		this.atualizarDados(obj, novoCli);
		return repository.save(novoCli);
	}

	private void atualizarDados(Cliente objComDadosInseridos, Cliente cliBuscadoNoBanco) {
		cliBuscadoNoBanco.setNome(objComDadosInseridos.getNome());
		cliBuscadoNoBanco.setEmail(objComDadosInseridos.getEmail());

	}

	public Page<Cliente> listarPaginacao(Integer page, Integer linesPerPage, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repository.findAll(pageRequest);
	}

}
