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

import com.br.curso.domain.Pedido;
import com.br.curso.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService service;

	@GetMapping()
	public Collection<Pedido> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Pedido> salvar(@Valid @RequestBody Pedido obj) {
		obj = service.salvar(obj);
		if (obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		throw new RuntimeException("Erro ao salvar pedido.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id){
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping("/{id}")
	public Collection<Pedido> excluir(@PathVariable Integer id){
		boolean response = service.excluir(id);
		if(response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir pedido");
	}
	
	@PutMapping("/{id}")
	public Pedido atualizar(@PathVariable Integer idCat, @RequestBody Pedido obj) {
		obj.setId(idCat);
		obj = service.atualizar(obj);
		if(obj != null) {
			return obj;
		}
		throw new RuntimeException("Erro ao atualizar pedido");
	}
}
