package com.br.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cidade;
import com.br.curso.domain.Estado;
import com.br.curso.repository.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository repository;

	public List<Estado> listarTodos() {
		return repository.findAllByOrderByNome();
	}
}
