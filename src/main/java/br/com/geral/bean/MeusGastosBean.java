package br.com.geral.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import br.com.geral.bo.MeusGastosBO;
import br.com.geral.enums.SimNaoEnum;
import br.com.geral.enums.SumarioMensagem;
import br.com.geral.model.GastosMensais;

@ManagedBean
@ViewScoped
public class MeusGastosBean extends GenericBean {
	
	private GastosMensais fonteSelecionada;
	
	private MeusGastosBO bo;
	
	private String fonteEntrada;
	private BigDecimal valorEntrada;
	private SimNaoEnum ativoEntrada;
	
	public MeusGastosBean() {
		super();
		this.bo = new MeusGastosBO();
	}

	@PostConstruct
	public void init(){
		this.valorEntrada = BigDecimal.ZERO;
		this.listaGastosMensais = new ArrayList<GastosMensais>();
		
		try {
			this.setListaGastosMensais(bo.retornarMeusGastos());
			this.setListaHistoricoMeusGastos(bo.retornarHistoricoMeusGastos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarNovaFonte() {
		
		if(this.fonteEntrada == null || this.fonteEntrada.isEmpty()) {
			enviaMensagem(FacesMessage.SEVERITY_WARN, SumarioMensagem.ATENCAO, "Necessário adicionar fonte.");
			return;
		}
		
		if(this.ativoEntrada == null) {
			this.ativoEntrada = SimNaoEnum.S;
		}
		Long idRegistroNovo = (long) listaGastosMensais.size()+1;
		
		this.listaGastosMensais.add(new GastosMensais(idRegistroNovo, this.fonteEntrada, SimNaoEnum.N, this.ativoEntrada, this.valorEntrada));
		limparSelecao();
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha adicionada.");
	}
	
    public void limparSelecao() {
    	this.fonteEntrada = new String();
    	this.ativoEntrada = null;
    	this.valorEntrada = BigDecimal.ZERO;
    	
    }
	
	public void removerItem() {
		this.listaGastosMensais.remove(this.fonteSelecionada);
		enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Linha removida.");
	}
	
    public void onCellEdit(CellEditEvent<?> event) {
        Object antigoValor = event.getOldValue();
        Object novoValor = event.getNewValue();
        
        if (novoValor != null && !novoValor.equals(antigoValor)) {
        	enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.EDITADO, 
        			"antigo valor: " + antigoValor + ", novo valor: " + novoValor + ".");
        }
    }
    
    public void salvarMeusGastos () {
    	try {
    		
    		bo.salvarMeusGastos(this.listaGastosMensais);
    		bo.salvarHistoricoMeusGastos(this.listaHistoricoMeusGastos);
        	
        	enviaMensagem(FacesMessage.SEVERITY_INFO, SumarioMensagem.SUCESSO, "Registro salvo.");
        	
		} catch (Exception e) {
			enviaMensagem(FacesMessage.SEVERITY_ERROR, SumarioMensagem.ERRO, "Não salvo.");
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
