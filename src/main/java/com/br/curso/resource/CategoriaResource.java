package com.br.curso.resource;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.curso.domain.Categoria;
import com.br.curso.dto.CategoriaDTO;
import com.br.curso.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;

	@GetMapping()
	public List<CategoriaDTO> listar() {
		List<Categoria> listaCats = service.listar();
		List<CategoriaDTO> listaDtos = listaCats.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listaDtos;
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
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
}
