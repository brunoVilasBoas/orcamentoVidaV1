package br.com.geral.bo;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.model.GastosMensais;
import br.com.geral.model.HistoricoMeusGastos;
import br.com.geral.util.DateUtil;

public class MeusGastosBO {

	private CSVWriter csvWriter;


	public void salvarMeusGastos(List<GastosMensais> listaGastosMensais) throws IOException, Exception {
		
		String[] cabecalho = {"id", "fonte", "gastoPessoal", "ativo", "valor"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeusGastos.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(GastosMensais g : listaGastosMensais) {
        	linhas.add(new String[]{g.getId().toString(),
        							g.getFonte(),
        							g.getGastoPessoal().getdescResumida(),
        							g.getAtivo().getdescResumida(),
        							g.getValor().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
		
	}
	
	public List<HistoricoMeusGastos> salvarHistoricoMeusGastos(List<HistoricoMeusGastos> listaHistoricoGastosMensais) throws IOException, Exception {
		
		String[] cabecalho = {"id", "data", "valorGastoMes"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuHistoricoGastos.csv"));
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
	
	public List<GastosMensais> retornarMeusGastos() throws IOException, CsvException{
		
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeusGastos.csv"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        
        List<String[]> registros = csvReader.readAll();
        List<GastosMensais> listaGastos = new ArrayList<GastosMensais>();
        
        for (String[] registro : registros) {
        	
        	listaGastos.add(new GastosMensais(Long.parseLong(registro[0]),
        									  registro[1].toString(),
        									  SimNaoEnum.convertStringSN(registro[2]),
        									  SimNaoEnum.convertStringSN(registro[3]),
        									  new BigDecimal(registro[4])));
        }
        
        return listaGastos;
		
	}
	
	public List<HistoricoMeusGastos> retornarHistoricoMeusGastos() throws Exception{
		try {	
	        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuHistoricoGastos.csv"));
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

}
