package com.br.curso.domain.enuns;

public enum TipoCliente {
	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA (2, "Pessoa Jurídica");

	private String descricao;
	private Integer cod;
	
	private TipoCliente(int cod, String des) {
		this.descricao = des;
		this.cod = cod;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(TipoCliente x : TipoCliente.values()) {
			if(codigo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + codigo);
	}
}
