package com.br.curso.domain;

import javax.persistence.Entity;

import com.br.curso.domain.enuns.EstadoPagamento;
@Entity
public class PagamentoComCartao extends Pagamento {
	private Integer numeroDeParcelas;

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroParcelas;

	}

	public PagamentoComCartao() {
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	
	
}
