package com.br.curso.resource;

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

import com.br.curso.domain.Cidade;
import com.br.curso.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	@Autowired
	private CidadeService service;

	@GetMapping()
	public Collection<Cidade> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Cidade> salvar(@Valid @RequestBody Cidade cat) {
		cat = service.salvar(cat);
		if (cat != null) {
			return ResponseEntity.ok().body(cat);
		}
		throw new RuntimeException("Erro ao salvar categoria.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorId(@PathVariable Integer id){
		Cidade cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@DeleteMapping("/{id}")
	public Collection<Cidade> excluir(@PathVariable Integer id){
		boolean response = service.excluir(id);
		if(response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir Cidade");
	}
	
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Integer idCat, @RequestBody Cidade cat) {
		cat.setId(idCat);
		cat = service.atualizar(cat);
		if(cat != null) {
			return cat;
		}
		throw new RuntimeException("Erro ao atualizar Cidade");
	}
}
