package br.com.geral.bean;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.enums.SumarioMensagem;
import br.com.geral.model.MeuDinheiro;
import br.com.geral.model.RendimentosMensais;

@ManagedBean
@ViewScoped
public class MeuDinheiroBean extends GenericBean {
	
	private MeuDinheiro fonteSelecionada;
	private RendimentosMensais rendimentoSelecionada;
	
	private String fonteEntrada;
	private BigDecimal valorEntrada = BigDecimal.ZERO;
	private SimNaoEnum disponibilidadeEntrada;
	
	@PostConstruct
	public void init(){
		CargaListas();
	}
	
	public void adicionarNovaFonte() {
		
		if(this.fonteEntrada == null || this.fonteEntrada.isEmpty()) {
			enviaMensagem(FacesMessage.SEVERITY_WARN, SumarioMensagem.ATENCAO, "Necessário adicionar fonte.");
			return;
		}
		
		if(this.disponibilidadeEntrada == null) {
			this.disponibilidadeEntrada = SimNaoEnum.N;
		}
		
		this.listaMeuDinheiro.add(new MeuDinheiro(null, this.fonteEntrada, this.disponibilidadeEntrada, this.valorEntrada));
		
		limparSelecao();
		
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha adicionada.");
	}
	
	public void removerItem() {
		this.listaMeuDinheiro.remove(this.fonteSelecionada);
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha removida.");
	}
	
	public void adicionarNovoRendimento() {
		
		if(this.fonteEntrada == null || this.fonteEntrada.isEmpty()) {
			enviaMensagem(FacesMessage.SEVERITY_WARN, SumarioMensagem.ATENCAO, "Necessário adicionar fonte.");
			return;
		}
		
		this.listaRendimentosMensais.add(new RendimentosMensais(null, this.fonteEntrada, SimNaoEnum.S, this.valorEntrada));
		
		limparSelecao();
		
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha adicionada.");
	}
	
	public void removerItemRendimento() {
		this.listaRendimentosMensais.remove(this.rendimentoSelecionada);
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha removida.");
	}
	
    public void onCellEdit(CellEditEvent<?> event) {
        Object antigoValor = event.getOldValue();
        Object novoValor = event.getNewValue();

        if (novoValor != null && !novoValor.equals(antigoValor)) {
            enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Campo editado - antigo valor: " + antigoValor + ", novo valor:" + novoValor + ";");
        }
    }
    
    public void limparSelecao () {
    	this.fonteEntrada = new String();
    	this.valorEntrada = BigDecimal.ZERO;
    	this.disponibilidadeEntrada = null;
    	
    }

	// GETTER AND SETTERS
    
    
    public MeuDinheiro getFonteSelecionada() {
		return fonteSelecionada;
	}

	public void setFonteSelecionada(MeuDinheiro fonteSelecionada) {
		this.fonteSelecionada = fonteSelecionada;
	}

	public RendimentosMensais getRendimentoSelecionada() {
		return rendimentoSelecionada;
	}

	public void setRendimentoSelecionada(RendimentosMensais rendimentoSelecionada) {
		this.rendimentoSelecionada = rendimentoSelecionada;
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

	public SimNaoEnum getDisponibilidadeEntrada() {
		return disponibilidadeEntrada;
	}

	public void setDisponibilidadeEntrada(SimNaoEnum disponibilidadeEntrada) {
		this.disponibilidadeEntrada = disponibilidadeEntrada;
	}
	
}
