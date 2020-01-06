package com.br.curso.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.br.curso.domain.Categoria;

public class CategoriaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer id;

	public CategoriaDTO(Categoria cat) {
		this.nome = cat.getNome();
		this.id = cat.getId();
	}
	
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
