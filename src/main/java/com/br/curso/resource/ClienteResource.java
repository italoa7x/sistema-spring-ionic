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

import com.br.curso.domain.Cliente;
import com.br.curso.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;

	@GetMapping()
	public Collection<Cliente> listar() {
		return service.listar();
	}

	@PostMapping()
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cat) {
		cat = service.salvar(cat);
		if (cat != null) {
			return ResponseEntity.ok().body(cat);
		}
		throw new RuntimeException("Erro ao salvar cliente.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id){
		Cliente cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@DeleteMapping("/{id}")
	public Collection<Cliente> excluir(@PathVariable Integer id){
		boolean response = service.excluir(id);
		if(response) {
			return service.listar();
		}
		throw new RuntimeException("Erro ao excluir cliente");
	}
	
	@PutMapping("/{id}")
	public Cliente atualizar(@PathVariable Integer idCli, @RequestBody Cliente obj) {
		obj.setId(idCli);
		obj = service.atualizar(obj);
		if(obj != null) {
			return obj;
		}
		throw new RuntimeException("Erro ao atualizar cliente");
	}
}
