package br.com.geral.bo;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.model.GastosMensais;
import br.com.geral.model.HistoricoMeusGastos;
import br.com.geral.util.DateUtil;
import br.com.geral.util.ImportUtil;

public class MeusGastosBO {

	private CSVWriter csvWriter;
	
	private String pathURL;

	public MeusGastosBO() {
		setPathURL(ImportUtil.retornarCaminhoCSV());
	}
	
	
	public void salvarMeusGastos(List<GastosMensais> listaGastosMensais) throws IOException, Exception {
		
		String[] cabecalho = {"id", "fonte", "gastoPessoal", "ativo", "dataVencimento", "pago", "valor"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get(pathURL + "MeusGastos.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(GastosMensais g : listaGastosMensais) {
        	linhas.add(new String[]{g.getId().toString(),
        							g.getFonte(),
        							g.getGastoPessoal().getdescResumida(),
        							g.getAtivo().getdescResumida(),
        							DateUtil.dataFormatadaBr(g.getDataVencimento()),
        							g.getPago().getdescResumida(),
        							g.getValor().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
		
	}
	
	public List<HistoricoMeusGastos> salvarHistoricoMeusGastos(List<HistoricoMeusGastos> listaHistoricoGastosMensais) throws IOException, Exception {
		
		String[] cabecalho = {"id", "data", "valorGastoMes"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get(pathURL + "MeuHistoricoGastos.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(HistoricoMeusGastos h : listaHistoricoGastosMensais) {
        	linhas.add(new String[]{h.getId().toString(),
        							DateUtil.dataFormatadaBr(h.getData()),
        							h.getValorGastoMes().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
        
        return retornarHistoricoMeusGastos();
		
	}
	
	public List<GastosMensais> retornarMeusGastos() throws IOException, CsvException, NumberFormatException, ParseException{
		
        Reader reader = Files.newBufferedReader(Paths.get(pathURL + "MeusGastos.csv"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        
        List<String[]> registros = csvReader.readAll();
        List<GastosMensais> listaGastos = new ArrayList<GastosMensais>();
        
        for (String[] registro : registros) {
        	
        	listaGastos.add(new GastosMensais(Long.parseLong(registro[0]),
        									  registro[1].toString(),
        									  SimNaoEnum.convertStringSN(registro[2]),
        									  SimNaoEnum.convertStringSN(registro[3]),
        									  DateUtil.stringParaDate(registro[4].toString()),
        									  SimNaoEnum.convertStringSN(registro[5]),
        									  new BigDecimal(registro[6])));
        }
        
        return listaGastos;
		
	}
	
	public List<HistoricoMeusGastos> retornarHistoricoMeusGastos() throws Exception{
		try {	
	        Reader reader = Files.newBufferedReader(Paths.get(pathURL + "MeuHistoricoGastos.csv"));
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        
	        List<String[]> registros = csvReader.readAll();
	        List<HistoricoMeusGastos> listaHistoricoGastos = new ArrayList<HistoricoMeusGastos>();
	        
	        for (String[] registro : registros) {
	        	
	        	listaHistoricoGastos.add(new HistoricoMeusGastos(Long.parseLong(registro[0]),
	        									  DateUtil.stringParaDate(registro[1]),
	        									  new BigDecimal(registro[2])));
	        }
	        return listaHistoricoGastos;
	        
		} catch (NoSuchFileException e) {
			List<HistoricoMeusGastos> listaHistoricoGastos = new ArrayList<HistoricoMeusGastos>();
			
			listaHistoricoGastos.add(new HistoricoMeusGastos(1L, new Date(), BigDecimal.ZERO));
			
			return salvarHistoricoMeusGastos(listaHistoricoGastos);
		}
	}


	public String getPathURL() {
		return pathURL;
	}


	public void setPathURL(String pathURL) {
		this.pathURL = pathURL;
	}

}
