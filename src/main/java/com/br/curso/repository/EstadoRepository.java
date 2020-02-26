package com.br.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.curso.domain.Cidade;
import com.br.curso.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly = true)
	List<Estado> findAllByOrderByNome();

	
}
