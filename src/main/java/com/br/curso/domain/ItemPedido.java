package com.br.curso.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private double desconto;
	private Integer quantidade;
	private double preco;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Produto prod, Pedido ped, double desconto, Integer quantidade, double preco) {
		super();
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
		this.id.setPedido(ped);
		this.id.setProduto(prod);
	}

	public double getSubtotal() {
		return (preco - desconto) * quantidade;
	}
	
	public void setPedido(Pedido p) {
		this.id.setPedido(p);
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setProduto(Produto prod) {
		this.id.setProduto(prod);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qtd: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubtotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	

}
