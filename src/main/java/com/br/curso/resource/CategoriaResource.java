package com.br.curso.resource;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.curso.domain.Categoria;
import com.br.curso.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;

	@GetMapping()
	public Collection<Categoria> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria cat) {
		cat = service.salvar(cat);
		if (cat != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("categorias/{id}")
					.buildAndExpand(cat.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		throw new RuntimeException("Erro ao salvar categoria.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
		Categoria cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}

	@DeleteMapping("/{id}")
	public Collection<Categoria> excluir(@PathVariable Integer id) {
		boolean response = service.excluir(id);
		if (response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir categoria");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
}
