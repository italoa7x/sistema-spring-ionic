package com.br.curso.domain.enuns;

public enum EstadoPagamento {
	PENDENTE (1, "Pendente"),
	QUITADO (2, "Quitado"),
	CANCELADO (3, "Cancelado");

	private String descricao;
	private Integer cod;
	
	private EstadoPagamento(int cod, String des) {
		this.descricao = des;
		this.cod = cod;
	}
	
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(codigo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + codigo);
	}
}
