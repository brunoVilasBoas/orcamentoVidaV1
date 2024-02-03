package br.com.geral.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.geral.bo.MeusGastosBO;
import br.com.geral.enums.SimNaoEnum;
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
		
		
		this.listaMeuDinheiro = new ArrayList<MeuDinheiro>();
		this.listaRendimentosMensais = new ArrayList<RendimentosMensais>();
		
		this.listaMeuDinheiro.add(new MeuDinheiro(1L, "Nubank", SimNaoEnum.S, new BigDecimal(40162.04)));
		this.listaMeuDinheiro.add(new MeuDinheiro(2L, "Itáu", SimNaoEnum.S, new BigDecimal(25131.47)));
		this.listaMeuDinheiro.add(new MeuDinheiro(3L, "FGTS", SimNaoEnum.N, new BigDecimal(41875.13)));
		this.listaMeuDinheiro.add(new MeuDinheiro(4L, "Meliuz", SimNaoEnum.N, new BigDecimal(41.10)));

		this.listaRendimentosMensais.add(new RendimentosMensais(1L, "CDB DI Itáu", SimNaoEnum.S, new BigDecimal(40162.04)));
		this.listaRendimentosMensais.add(new RendimentosMensais(2L, "Vale Alimentação", SimNaoEnum.S, new BigDecimal(951.72)));
		this.listaRendimentosMensais.add(new RendimentosMensais(3L, "Rendimento Nu", SimNaoEnum.S, new BigDecimal(338.45)));
	
		
		this.listaHistoricoMeuDinheiro = new ArrayList<HistoricoMeuDinheiro>();
	
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(1L, new Date(2023, 1, 8), new BigDecimal(52537.43), new BigDecimal(99483.07)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(2L, new Date(2023, 2, 8), new BigDecimal(51954.22), new BigDecimal(99609.19)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(3L, new Date(2023, 3, 8), new BigDecimal(51737.61), new BigDecimal(100065.14)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(4L, new Date(2023, 4, 8), new BigDecimal(51658.67), new BigDecimal(100711.41)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(5L, new Date(2023, 5, 8), new BigDecimal(53535.72), new BigDecimal(103266.03)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(6L, new Date(2023, 6, 8), new BigDecimal(49691.82), new BigDecimal(100146.37)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(7L, new Date(2023, 7, 8), new BigDecimal(50045.52), new BigDecimal(101551.97)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(8L, new Date(2023, 8, 8), new BigDecimal(52889.71), new BigDecimal(105856.4)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(8L, new Date(2023, 9, 8), new BigDecimal(67731.94), new BigDecimal(105740.46)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(9L, new Date(2023, 10, 8), new BigDecimal(68149.2), new BigDecimal(106869.72)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(10L, new Date(2023, 11, 8), new BigDecimal(71205.52), new BigDecimal(109925.89)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(11L, new Date(2023, 12, 8), new BigDecimal(73496.75), new BigDecimal(113048.66)));
		this.listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(11L, new Date(2024, 1, 8), new BigDecimal(73657.36), new BigDecimal(115532.49)));
		
		this.listaHistoricoMeusGastos = new ArrayList<HistoricoMeusGastos>();
		
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(1L, new Date(2022, 12, 8), new BigDecimal(10790.53)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(2L, new Date(2023, 1, 8), new BigDecimal(6864.85)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(3L, new Date(2023, 2, 8), new BigDecimal(6269.63)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(4L, new Date(2023, 3, 8), new BigDecimal(6552.51)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(5L, new Date(2023, 4, 8), new BigDecimal(6425.46)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(6L, new Date(2023, 5, 8), new BigDecimal(8488.85)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(7L, new Date(2023, 6, 8), new BigDecimal(5391.51)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(8L, new Date(2023, 7, 8), new BigDecimal(6463.32)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(9L, new Date(2023, 8, 8), new BigDecimal(5696.68)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(10L, new Date(2023, 9, 8), new BigDecimal(6547.06)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(11L, new Date(2023, 11, 8), new BigDecimal(7439.88)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(12L, new Date(2023, 12, 8), new BigDecimal(10270.69)));
		this.listaHistoricoMeusGastos.add(new HistoricoMeusGastos(13L, new Date(2024, 1, 8), new BigDecimal(7469.02)));
		
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
