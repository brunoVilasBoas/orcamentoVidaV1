package br.com.geral.bean;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.enums.SumarioMensagem;
import br.com.geral.model.GastosMensais;

@ManagedBean
@ViewScoped
public class MeusGastosBean extends GenericBean {
	
	private GastosMensais fonteSelecionada;
	
	private String fonteEntrada;
	private BigDecimal valorEntrada;
	private SimNaoEnum disponibilidadeEntrada;
	
	@PostConstruct
	public void init(){
		this.valorEntrada = BigDecimal.ZERO;
		CargaListas();
	}
	
	public void adicionarNovaFonte() {
		
		if(this.fonteEntrada == null || this.fonteEntrada.isEmpty()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, SumarioMensagem.ATENCAO.toString(), "Necessário adicionar fonte.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		if(this.disponibilidadeEntrada == null) {
			this.disponibilidadeEntrada = SimNaoEnum.N;
		}
		
		this.listaGastosMensais.add(new GastosMensais(null, this.fonteEntrada, this.disponibilidadeEntrada, this.valorEntrada));
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO.toString(), "Linha adicionada.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void removerItem() {
		this.listaGastosMensais.remove(this.fonteSelecionada);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO.toString(), "Linha removida.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
    public void onCellEdit(CellEditEvent<?> event) {
        Object antigoValor = event.getOldValue();
        Object novoValor = event.getNewValue();

        if (novoValor != null && !novoValor.equals(antigoValor)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Linha editada", "antigo valor: " + antigoValor + ", novo valor:" + novoValor);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

	// GETTER AND SETTERS
    
    
    public GastosMensais getFonteSelecionada() {
		return fonteSelecionada;
	}

	public void setFonteSelecionada(GastosMensais fonteSelecionada) {
		this.fonteSelecionada = fonteSelecionada;
	}

	public String getFonteEntrada() {
		return fonteEntrada;
	}

	public void setFonteEntrada(String fonteEntrada) {
		this.fonteEntrada = fonteEntrada;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	
	
}
