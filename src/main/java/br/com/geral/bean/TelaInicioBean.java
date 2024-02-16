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
import br.com.geral.model.HistoricoMeusGastos;

@ManagedBean
@ViewScoped
public class TelaInicioBean extends GenericBean {
	
	private LineChartModel lineModelRendimentosMensais;
	private LineChartModel lineModelGastosMensais;
	
	public TelaInicioBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init(){
		CargaListas();
		
		criarLineModelRendimentosMensais();
		criarLineModelGastosMensais();
		
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
        dataSetValorDisponivel.setLabel("Valor Dispinível do Mês");
        dataSetValorDisponivel.setBorderColor("rgb(75, 192, 192)");
        dataSetValorDisponivel.setBackgroundColor("rgb(75, 192, 192)");
        dataSetValorDisponivel.setTension(0.1);
        
        dataSetValorTotal.setData(valorTotal);
        dataSetValorTotal.setFill(false);
        dataSetValorTotal.setLabel("Valor Total do Mês");
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
        title.setText("Crescimento Mensal da Minha Fortuna Disponível");
        options.setTitle(title);

        lineModelRendimentosMensais.setOptions(options);
        lineModelRendimentosMensais.setData(data);
    }
    
    public void criarLineModelGastosMensais() {
    	this.lineModelGastosMensais = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSetGastoTotal = new LineChartDataSet();
        
        
        List<Object> valorGasto = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        for(HistoricoMeusGastos h : this.listaHistoricoMeusGastos) {
        	valorGasto.add(h.getValorGastoMes());
        	labels.add(formataDataPadraoBrasileiro(h.getData()));
        }
        
        dataSetGastoTotal.setData(valorGasto);
        dataSetGastoTotal.setFill(false);
        dataSetGastoTotal.setLabel("Valor gasto no mês");
        dataSetGastoTotal.setBorderColor("rgba(234, 67, 53)");
        dataSetGastoTotal.setBackgroundColor("rgba(234, 67, 53)");
        dataSetGastoTotal.setTension(0.1);
        
        data.setLabels(labels);
        data.addChartDataSet(dataSetGastoTotal);
   
        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Gastos Mensais");
        options.setTitle(title);

        lineModelGastosMensais.setOptions(options);
        lineModelGastosMensais.setData(data);
    }
    
	/**
	 * Formata a data informada para o padrão brasileiro ou seja dd/MM/yyyy
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

	public LineChartModel getLineModelGastosMensais() {
		return lineModelGastosMensais;
	}

	public void setLineModelGastosMensais(LineChartModel lineModelGastosMensais) {
		this.lineModelGastosMensais = lineModelGastosMensais;
	}
	
	
	
}
