package br.com.geral.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.geral.bo.MeuDinheiroBO;
import br.com.geral.bo.MeusGastosBO;
import br.com.geral.enums.SumarioMensagem;
import br.com.geral.model.GastosMensais;
import br.com.geral.model.HistoricoMeuDinheiro;
import br.com.geral.model.HistoricoMeusGastos;
import br.com.geral.model.MeuDinheiro;
import br.com.geral.model.RendimentosMensais;


/**
 * Classe abstrata utilizada pelo padrão da arquitetura no que se refere a
 * utilização do Manager Bean
 * 
 * @author Bruno Ordoni Vilas Boas
 */
public abstract class GenericBean {

	protected SumarioMensagem sumarioMensagem;
	
	protected MeuDinheiroBO boMeuDinheiro;
	protected MeusGastosBO boMeusGastos;
	
	protected List<GastosMensais> listaGastosMensais;
	protected List<MeuDinheiro> listaMeuDinheiro;
	protected List<RendimentosMensais> listaRendimentosMensais;
	
	protected List<HistoricoMeuDinheiro> listaHistoricoMeuDinheiro;
	protected List<HistoricoMeusGastos> listaHistoricoMeusGastos;
	
	private static final String MENSAGEM_SUCESSO  = "Operação realizada com sucesso.";
	
	protected void enviaMensagemPadraoSucesso() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO.toString(), MENSAGEM_SUCESSO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Envia uma mensagem global para o componente message do xhtml
	 * 
	 * @param severidadeErro
	 * @param sumarioMensagem
	 * @param detalheMensagem
	 */
	protected void enviaMensagem(Severity severidadeErro, SumarioMensagem sumarioMensagem, String detalheMensagem) {
		FacesMessage msg = new FacesMessage(severidadeErro, sumarioMensagem.toString(), detalheMensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void CargaListas(){
		this.boMeuDinheiro = new MeuDinheiroBO();
		this.boMeusGastos = new MeusGastosBO();
		
		
		this.listaMeuDinheiro = new ArrayList<MeuDinheiro>();
		this.listaRendimentosMensais = new ArrayList<RendimentosMensais>();
		this.listaHistoricoMeuDinheiro = new ArrayList<HistoricoMeuDinheiro>();
		
		this.listaGastosMensais = new ArrayList<GastosMensais>();
		this.listaHistoricoMeusGastos = new ArrayList<HistoricoMeusGastos>();
		
		try {
			this.listaMeuDinheiro.addAll(boMeuDinheiro.retornarMeuDinheiro());
			this.listaRendimentosMensais.addAll(boMeuDinheiro.retornarMeusRendimentos());
			this.listaHistoricoMeuDinheiro.addAll(boMeuDinheiro.retornarHistoricoMeuDinheiro());
			
			this.listaGastosMensais.addAll(boMeusGastos.retornarMeusGastos());
			this.listaHistoricoMeusGastos.addAll(boMeusGastos.retornarHistoricoMeusGastos());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	// GETTER AND SETTERS
	
	public List<GastosMensais> getListaGastosMensais() {
		return listaGastosMensais;
	}
	
	public void setListaGastosMensais(List<GastosMensais> listaGastosMensais) {
		this.listaGastosMensais = listaGastosMensais;
	}
	
	public List<MeuDinheiro> getListaMeuDinheiro() {
		return listaMeuDinheiro;
	}
	
	public void setListaMeuDinheiro(List<MeuDinheiro> listaMeuDinheiro) {
		this.listaMeuDinheiro = listaMeuDinheiro;
	}
	
	public List<RendimentosMensais> getListaRendimentosMensais() {
		return listaRendimentosMensais;
	}
	
	public void setListaRendimentosMensais(List<RendimentosMensais> listaRendimentosMensais) {
		this.listaRendimentosMensais = listaRendimentosMensais;
	}
	
	public List<HistoricoMeuDinheiro> getListaHistoricoMeuDinheiro() {
		return listaHistoricoMeuDinheiro;
	}

	public void setListaHistoricoMeuDinheiro(List<HistoricoMeuDinheiro> listaHistoricoMeuDinheiro) {
		this.listaHistoricoMeuDinheiro = listaHistoricoMeuDinheiro;
	}

	public List<HistoricoMeusGastos> getListaHistoricoMeusGastos() {
		return listaHistoricoMeusGastos;
	}

	public void setListaHistoricoMeusGastos(List<HistoricoMeusGastos> listaHistoricoMeusGastos) {
		this.listaHistoricoMeusGastos = listaHistoricoMeusGastos;
	}

	public BigDecimal getDinheiroDisponivel() {
        if(listaMeuDinheiro == null || listaMeuDinheiro.isEmpty()) {
        	return BigDecimal.ZERO;
        }
		double[] somatorio = { 0.0 };

        listaMeuDinheiro.forEach(objeto -> somatorio[0] += objeto.getDisponivel().isSim() ? objeto.getValor().doubleValue() : 0);

		return new BigDecimal(somatorio[0]);
	}
	
	public BigDecimal getTotalDinheiro() {
        if(listaMeuDinheiro == null || listaMeuDinheiro.isEmpty()) {
        	return BigDecimal.ZERO;
        }
		double[] somatorio = { 0.0 };

        listaMeuDinheiro.forEach(objeto -> somatorio[0] += objeto.getValor().doubleValue());

		return new BigDecimal(somatorio[0]);
	}
	
	public BigDecimal getGanhosMensais() {
        if(listaRendimentosMensais == null || listaRendimentosMensais.isEmpty()) {
        	return BigDecimal.ZERO;
        }
		double[] somatorio = { 0.0 };

        listaRendimentosMensais.forEach(objeto -> somatorio[0] += objeto.getAtivo().isSim() ? objeto.getValor().doubleValue() : 0);

		return new BigDecimal(somatorio[0]);
	}
	
	public BigDecimal getGastosMensais() {
        if(listaGastosMensais == null || listaGastosMensais.isEmpty()) {
        	return BigDecimal.ZERO;
        }
		double[] somatorio = { 0.0 };

        listaGastosMensais.forEach(objeto -> somatorio[0] += objeto.getAtivo().isSim() ? objeto.getValor().doubleValue() : 0);

		return new BigDecimal(somatorio[0]);
	}
	
	public BigDecimal getRendimentosMensais() {
		return getGanhosMensais().subtract(getGastosMensais());
	}


}
