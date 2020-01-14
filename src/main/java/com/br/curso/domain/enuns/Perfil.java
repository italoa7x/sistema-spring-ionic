package com.br.curso.domain.enuns;

public enum Perfil {
	ADMIN (1, "ROLE_ADMIN"),
	CLIENTE (2, "ROLE_CLIENTE");

	private String descricao;
	private Integer cod;
	
	private Perfil(int cod, String des) {
		this.descricao = des;
		this.cod = cod;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(Perfil x : Perfil.values()) {
			if(codigo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + codigo);
	}
}
