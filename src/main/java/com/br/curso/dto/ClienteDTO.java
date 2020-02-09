package com.br.curso.dto;

import java.io.Serializable;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.curso.domain.Cliente;
import com.br.curso.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "O nome é obrigatório")
	@Length(min = 20, max = 100, message = "O nome deve conter entre 20 e 100 caracteris")
	private String nome;

	@NotEmpty(message = "O e-mail é obrigatório.")
	@Email(message = "Email invalido")
	private String email;

	private Integer id;

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente cli) {
		this.nome = cli.getNome();
		this.email = cli.getEmail();
		this.id = cli.getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
