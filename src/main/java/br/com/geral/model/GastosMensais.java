package br.com.geral.model;

import java.math.BigDecimal;
import java.util.Date;

import br.com.geral.enums.SimNaoEnum;

public class GastosMensais {
	
	private Long id;
	private String fonte;
	private SimNaoEnum gastoPessoal;
	private SimNaoEnum ativo;
	private Date dataVencimento;
	private SimNaoEnum pago;
	private BigDecimal valor;
	
	public GastosMensais(Long id, String fonte, SimNaoEnum gastoPessoal, SimNaoEnum ativo, BigDecimal valor) {
		super();
		this.id = id;
		this.fonte = fonte;
		this.gastoPessoal = gastoPessoal;
		this.ativo = ativo;
		this.valor = valor;
	}
	
	public GastosMensais(Long id, String fonte, SimNaoEnum gastoPessoal, SimNaoEnum ativo, 
							Date dataVencimento, SimNaoEnum pago, BigDecimal valor) {
		super();
		this.id = id;
		this.fonte = fonte;
		this.gastoPessoal = gastoPessoal;
		this.ativo = ativo;
		this.dataVencimento = dataVencimento;
		this.pago = pago;
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

	public SimNaoEnum getGastoPessoal() {
		return gastoPessoal;
	}

	public void setGastoPessoal(SimNaoEnum gastoPessoal) {
		this.gastoPessoal = gastoPessoal;
	}

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public SimNaoEnum getPago() {
		return pago;
	}

	public void setPago(SimNaoEnum pago) {
		this.pago = pago;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
