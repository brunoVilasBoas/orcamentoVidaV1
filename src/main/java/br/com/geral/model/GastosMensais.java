package br.com.geral.model;

import java.math.BigDecimal;

import br.com.geral.enums.SimNaoEnum;

public class GastosMensais {
	
	private Long id;
	private String fonte;
	private SimNaoEnum ativo;
	private BigDecimal valor;
	
	public GastosMensais(Long id, String fonte, SimNaoEnum ativo, BigDecimal valor) {
		super();
		this.id = id;
		this.fonte = fonte;
		this.ativo = ativo;
		this.valor = valor;
	}

	public GastosMensais() {
		// TODO Auto-generated constructor stub
	}
	
	// GETTER AND SETTERS

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

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
