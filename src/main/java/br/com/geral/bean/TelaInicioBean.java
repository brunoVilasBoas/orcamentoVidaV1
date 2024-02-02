package br.com.geral.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import br.com.geral.model.HistoricoMeuDinheiro;

@ManagedBean
@ViewScoped
public class TelaInicioBean extends GenericBean {
	
	private LineChartModel lineModelRendimentosMensais;
	
	public TelaInicioBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init(){
		CargaListas();
		
		criarLineModelRendimentosMensais();
		
		PrimeFaces.current().executeScript("mudarCor("+getRendimentosMensais()+")");
	}
	
    public void criarLineModelRendimentosMensais() {
    	this.lineModelRendimentosMensais = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSetValorDisponivel = new LineChartDataSet();
        LineChartDataSet dataSetValorTotal = new LineChartDataSet();
        
        
        List<Object> valorDisponivel = new ArrayList<>();
        List<Object> valorTotal = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        for(HistoricoMeuDinheiro h : this.listaHistoricoMeuDinheiro) {
        	valorDisponivel.add(h.getValorDisponivelMes());
        	valorTotal.add(h.getValorTotalMes());
        	labels.add(formataDataPadraoBrasileiro(h.getData()));
        }
        
        dataSetValorDisponivel.setData(valorDisponivel);
        dataSetValorDisponivel.setFill(false);
        dataSetValorDisponivel.setLabel("Valor Dispin�vel do M�s");
        dataSetValorDisponivel.setBorderColor("rgb(75, 192, 192)");
        dataSetValorDisponivel.setBackgroundColor("rgb(75, 192, 192)");
        dataSetValorDisponivel.setTension(0.1);
        
        dataSetValorTotal.setData(valorTotal);
        dataSetValorTotal.setFill(false);
        dataSetValorTotal.setLabel("Valor Total do M�s");
        dataSetValorTotal.setBorderColor("rgba(54, 162, 235)");
        dataSetValorTotal.setBackgroundColor("rgba(54, 162, 235)");
        dataSetValorTotal.setTension(0.1);
        
        data.setLabels(labels);
        data.addChartDataSet(dataSetValorDisponivel);
        data.addChartDataSet(dataSetValorTotal);
   
        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Crescimento Mensal da Minha Fortuna Dispon�vel");
        options.setTitle(title);

        lineModelRendimentosMensais.setOptions(options);
        lineModelRendimentosMensais.setData(data);
    }
    
	/**
	 * Formata a data informada para o padr�o brasileiro ou seja dd/MM/yyyy
	 * 
	 * @param data
	 * @return
	 */
	public static String formataDataPadraoBrasileiro(Date data) {
		String formato = "dd/MM/yy";

		SimpleDateFormat formatador = new SimpleDateFormat(formato);

		return formatador.format(data);
	}
	
	// GETTER AND SETTERS
	
	public LineChartModel getLineModelRendimentosMensais() {
		return lineModelRendimentosMensais;
	}

	public void setLineModelRendimentosMensais(LineChartModel lineModelRendimentosMensais) {
		this.lineModelRendimentosMensais = lineModelRendimentosMensais;
	}	
	
}
