package com.br.curso.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.domain.Cidade;
import com.br.curso.domain.Estado;
import com.br.curso.dto.CidadeDTO;
import com.br.curso.dto.EstadoDTO;
import com.br.curso.service.CidadeService;
import com.br.curso.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	@Autowired
	private EstadoService service;
	@Autowired
	private CidadeService cidadeService;

	@GetMapping()
	public ResponseEntity<List<EstadoDTO>> listarTodos(String nome) {
		List<Estado> estado = service.listarTodos();
		List<EstadoDTO> estadosDto = estado.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDto);
	}
	
	@GetMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> listarCidadesPorEstado(@PathVariable String estadoId) {
		List<Cidade> cidade = cidadeService.buscarEstado(Integer.parseInt(estadoId));
		List<CidadeDTO> cidadesDto = cidade.stream().map(c -> new CidadeDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDto);
	}
}
