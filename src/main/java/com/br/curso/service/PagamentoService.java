package com.br.curso.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Pagamento;
import com.br.curso.repository.PagamentoRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class PagamentoService {
	@Autowired
	private PagamentoRepository repository;
	
	
	public Pagamento salvar(Pagamento cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	public boolean excluir(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Collection<Pagamento> listar(){
		return repository.findAll();
	}
	
	public Pagamento buscar(Integer id) {
		Optional<Pagamento> cat = repository.findById(id);
		if(cat.isPresent() == false) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id
					+ ", Tipo: " + Pagamento.class.getName());
		}
		return cat.get();
	}
	public Pagamento atualizar(Pagamento cat) {
		cat = this.buscar(cat.getId());
		if(cat == null) {
			return null;
		}
		return repository.save(cat);
	}
}
