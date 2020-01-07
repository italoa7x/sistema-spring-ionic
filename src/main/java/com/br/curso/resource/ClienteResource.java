package com.br.curso.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.curso.domain.Cliente;
import com.br.curso.dto.ClienteDTO;
import com.br.curso.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<Cliente> listaCats = service.listar();
		List<ClienteDTO> listaDtos = listaCats.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDtos);
	}

	@PostMapping()
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody ClienteDTO catDto) {
		Cliente obj = service.salvar(service.fromDTO(catDto));
		if (obj != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("clientes/{id}")
					.buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		throw new RuntimeException("Erro ao salvar cliente.");

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
		Cliente cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		objDto.setId(id);
		Cliente obj = service.atualizar(service.fromDTO(objDto));
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> listarPaginacao(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> listaCats = service.listarPaginacao(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDtos = listaCats.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDtos);
	}
}
