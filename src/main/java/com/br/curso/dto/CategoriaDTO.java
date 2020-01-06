package com.br.curso.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.curso.domain.Categoria;

public class CategoriaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Insira um nome para a categoria.")
	@Length(min = 5, max = 80, message = "O nome deve ser entre 5 e 80 caracteris.")
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
