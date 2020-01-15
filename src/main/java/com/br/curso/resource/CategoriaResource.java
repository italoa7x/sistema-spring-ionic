package com.br.curso.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<CategoriaDTO>> listar() {
		List<Categoria> listaCats = service.listar();
		List<CategoriaDTO> listaDtos = listaCats.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDtos);
	}

	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@PostMapping()
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody CategoriaDTO catDto) {
		Categoria obj = service.salvar(service.fromDTO(catDto));
		if (obj != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("categorias/{id}")
					.buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		throw new RuntimeException("Erro ao salvar categoria.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
		Categoria cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);

	}
	
	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> listarPaginacao(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> listaCats = service.listarPaginacao(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDtos = listaCats.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDtos);
	}
}
