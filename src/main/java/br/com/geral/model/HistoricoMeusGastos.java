package br.com.geral.model;

import java.math.BigDecimal;
import java.util.Date;

public class HistoricoMeusGastos {
	
	private Long id;
	private Date data;
	private BigDecimal valorGastoMes;
	
	public HistoricoMeusGastos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoricoMeusGastos(Long id, Date data, BigDecimal valorGastoMes) {
		super();
		this.id = id;
		this.data = data;
		this.valorGastoMes = valorGastoMes;
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

	public BigDecimal getValorGastoMes() {
		return valorGastoMes;
	}

	public void setValorGastoMes(BigDecimal valorGastoMes) {
		this.valorGastoMes = valorGastoMes;
	}


	
	
}
