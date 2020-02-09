package com.br.curso.resource;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.domain.Produto;
import com.br.curso.dto.ProdutoDTO;
import com.br.curso.service.ProdutoService;
import com.br.curso.service.util.URL;

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
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto obj) {
		obj = service.salvar(obj);
		if (obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		throw new RuntimeException("Erro ao salvar pedido.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public Collection<Produto> excluir(@PathVariable Integer id) {
		boolean response = service.excluir(id);
		if (response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir produto");
	}

	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Integer idCat, @RequestBody Produto obj) {
		obj.setId(idCat);
		obj = service.atualizar(obj);
		if (obj != null) {
			return obj;
		}
		throw new RuntimeException("Erro ao atualizar produto");
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ProdutoDTO>> listarPaginacao(
			@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "categorias", defaultValue = "") String categoria,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
			
			List<Integer> ids = URL.decodeIntList(categoria);
			String nomeDecoded = URL.decodeParam(nome);
			
		Page<Produto> listaCats = service.buscaPaginada(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaDtos = listaCats.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDtos);
	}

}
