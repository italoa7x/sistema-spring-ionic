package com.br.curso.resource;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.domain.Produto;
import com.br.curso.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;

	@GetMapping()
	public Collection<Produto> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto prod) {
		prod = service.salvar(prod);
		if (prod != null) {
			return ResponseEntity.ok().body(prod);
		}
		throw new RuntimeException("Erro ao salvar categoria.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id){
		Produto cat = service.buscar(id);
		if(cat == null) {
			throw new RuntimeException("Produto não cadastrada");
		}
		return ResponseEntity.ok().body(cat);
	}
	
	@DeleteMapping("/{id}")
	public Collection<Produto> excluir(@PathVariable Integer id){
		boolean response = service.excluir(id);
		if(response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir produto");
	}
}
