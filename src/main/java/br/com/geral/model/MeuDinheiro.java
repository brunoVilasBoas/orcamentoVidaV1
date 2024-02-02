package br.com.geral.model;

import java.math.BigDecimal;

import br.com.geral.enums.SimNaoEnum;

public class MeuDinheiro {
	
	private Long id;
	private String fonte;
	private SimNaoEnum disponivel;
	private BigDecimal valor;
	
	public MeuDinheiro(Long id, String fonte, SimNaoEnum disponivel, BigDecimal valor) {
		super();
		this.id = id;
		this.fonte = fonte;
		this.disponivel = disponivel;
		this.valor = valor;
	}

	// GETTER AND SETTERS
	
	public MeuDinheiro() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public SimNaoEnum getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(SimNaoEnum disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
