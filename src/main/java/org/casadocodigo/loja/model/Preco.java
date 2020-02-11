package org.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable //indica que este cara embarca em um produto que o adiciona como element
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;
	
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
