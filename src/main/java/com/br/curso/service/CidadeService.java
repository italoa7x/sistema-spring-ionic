package com.br.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cidade;
import com.br.curso.repository.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository repository;


	public List<Cidade> buscarEstado(Integer estadoId) {
		return repository.findCidades(estadoId);
	}

}
