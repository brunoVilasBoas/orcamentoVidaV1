package br.com.geral.enums;

import java.io.Serializable;

public enum TipoImportacaoPlanilhasEnum implements Serializable {
	
	MEU_DINHEIRO(1L, "Meu Dinheiro"),
	MEUS_GASTOS(2L, "Meus Gastos"),
	MEUS_GANHOS(3L, "Meus Ganhos");


	
	private Long codigo;
	private String descricao;
	
	private TipoImportacaoPlanilhasEnum(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;

	}

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
