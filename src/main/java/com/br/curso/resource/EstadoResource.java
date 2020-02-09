package com.br.curso.resource;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.domain.Estado;
import com.br.curso.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	@Autowired
	private EstadoService service;

	@GetMapping()
	public Collection<Estado> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Estado> salvar(@Valid @RequestBody Estado obj) {
		obj = service.salvar(obj);
		if (obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		throw new RuntimeException("Erro ao salvar Estado.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Integer id) {
		Estado cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}

	@DeleteMapping("/{id}")
	public Collection<Estado> excluir(@PathVariable Integer id) {
		boolean response = service.excluir(id);
		if (response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir Estado");
	}

	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Integer idCat, @RequestBody Estado cat) {
		cat.setId(idCat);
		cat = service.atualizar(cat);
		if (cat != null) {
			return cat;
		}
		throw new RuntimeException("Erro ao atualizar Estado");
	}
}
