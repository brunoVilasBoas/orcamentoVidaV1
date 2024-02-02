package br.com.geral.model;

import java.math.BigDecimal;
import java.util.Date;

public class HistoricoMeuDinheiro {
	
	private Long id;
	private Date data;
	private BigDecimal valorDisponivelMes;
	private BigDecimal valorTotalMes;
	
	public HistoricoMeuDinheiro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoricoMeuDinheiro(Long id, Date data, BigDecimal valorDisponivelMes, BigDecimal valorTotalMes) {
		super();
		this.id = id;
		this.data = data;
		this.valorDisponivelMes = valorDisponivelMes;
		this.valorTotalMes = valorTotalMes;
	}
	
	// GETTER AND SETTERS
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValorDisponivelMes() {
		return valorDisponivelMes;
	}
	public void setValorDisponivelMes(BigDecimal valorDisponivelMes) {
		this.valorDisponivelMes = valorDisponivelMes;
	}
	public BigDecimal getValorTotalMes() {
		return valorTotalMes;
	}
	public void setValorTotalMes(BigDecimal valorTotalMes) {
		this.valorTotalMes = valorTotalMes;
	}
	
	
}
