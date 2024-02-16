package br.com.geral.bo;

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

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.model.HistoricoMeuDinheiro;
import br.com.geral.model.MeuDinheiro;
import br.com.geral.model.RendimentosMensais;
import br.com.geral.util.DateUtil;

public class MeuDinheiroBO {

	private CSVWriter csvWriter;


	public List<MeuDinheiro> salvarMeuDinheiro(List<MeuDinheiro> listaMeuDinheiro) throws IOException, Exception {
		
		String[] cabecalho = {"id", "fonte", "disponivel", "valor"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuDinheiro.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(MeuDinheiro g : listaMeuDinheiro) {
        	linhas.add(new String[]{g.getId().toString(),
        							g.getFonte(),
        							g.getDisponivel().getdescResumida(),
        							g.getValor().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
		
        return retornarMeuDinheiro();
	}
	
	public List<HistoricoMeuDinheiro> salvarHistoricoMeuDinheiro(List<HistoricoMeuDinheiro> listaHistoricoMeuDinheiro) throws IOException, Exception {
		
		String[] cabecalho = {"id", "data", "valorDisponivelMes", "valorTotalMes"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuHistoricoDinheiro.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(HistoricoMeuDinheiro h : listaHistoricoMeuDinheiro) {
        	linhas.add(new String[]{h.getId().toString(),
        							DateUtil.dataFormatadaBr(h.getData()),
        							h.getValorDisponivelMes().toString(),
        							h.getValorTotalMes().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
        
        return retornarHistoricoMeuDinheiro();
		
	}
	
	public List<RendimentosMensais> salvarMeusRendimentos(List<RendimentosMensais> listaRendimentosMensais) throws IOException, Exception {
		
		String[] cabecalho = {"id", "fonte", "ativo", "valor"};
		
		
        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeusRendimentos.csv"));
        this.csvWriter = new CSVWriter(writer);
        
        List<String[]> linhas = new ArrayList<>();
        
        for(RendimentosMensais g : listaRendimentosMensais) {
        	linhas.add(new String[]{g.getId().toString(),
        							g.getFonte(),
        							g.getAtivo().getdescResumida(),
        							g.getValor().toString()});
        }
        
        this.csvWriter.writeNext(cabecalho);
        this.csvWriter.writeAll(linhas);

        this.csvWriter.flush();
        writer.close();
        
		return listaRendimentosMensais;
		
	}
	
	public List<MeuDinheiro> retornarMeuDinheiro() throws Exception {
		try {
	        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuDinheiro.csv"));
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        
	        List<String[]> registros = csvReader.readAll();
	        List<MeuDinheiro> listaMeuDinheiro = new ArrayList<MeuDinheiro>();
	        
	        for (String[] registro : registros) {
	        	
	        	listaMeuDinheiro.add(new MeuDinheiro(Long.parseLong(registro[0]),
	        									  registro[1].toString(),
	        									  SimNaoEnum.convertStringSN(registro[2]),
	        									  new BigDecimal(registro[3])));
	        }
	        
	        return listaMeuDinheiro;
	       
		} catch (NoSuchFileException e) {
			List<MeuDinheiro> listaMeuDinheiro = new ArrayList<MeuDinheiro>();
			
			listaMeuDinheiro.add(new MeuDinheiro(1L, "Novo", SimNaoEnum.S, BigDecimal.ZERO));
			
			 return salvarMeuDinheiro(listaMeuDinheiro);
		}		
	}
	
	public List<HistoricoMeuDinheiro> retornarHistoricoMeuDinheiro() throws Exception{
		try {	
	        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeuHistoricoDinheiro.csv"));
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        
	        List<String[]> registros = csvReader.readAll();
	        List<HistoricoMeuDinheiro> listaHistoricoMeuDinheiro = new ArrayList<HistoricoMeuDinheiro>();
	        
	        for (String[] registro : registros) {
	        	
	        	listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(Long.parseLong(registro[0]),
	        									  DateUtil.stringParaDate(registro[1]),
	        									  new BigDecimal(registro[2]),
	        									  new BigDecimal(registro[3])));
	        }
	        return listaHistoricoMeuDinheiro;
	        
		} catch (NoSuchFileException e) {
			List<HistoricoMeuDinheiro> listaHistoricoMeuDinheiro = new ArrayList<HistoricoMeuDinheiro>();
			
			listaHistoricoMeuDinheiro.add(new HistoricoMeuDinheiro(1L, new Date(), BigDecimal.ZERO, BigDecimal.ZERO));
			
			return salvarHistoricoMeuDinheiro(listaHistoricoMeuDinheiro);
		}
	}

	public List<RendimentosMensais> retornarMeusRendimentos() throws Exception {
		try {	
	        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\bruno\\Desktop\\OrcamentoVidaRepositorio\\MeusRendimentos.csv"));
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        
	        List<String[]> registros = csvReader.readAll();
	        List<RendimentosMensais> listaMeusRendimentos = new ArrayList<RendimentosMensais>();
	        
	        for (String[] registro : registros) {
	        	
	        	listaMeusRendimentos.add(new RendimentosMensais(Long.parseLong(registro[0]),
	        													  registro[1].toString(),
					        									  SimNaoEnum.convertStringSN(registro[2]),
					        									  new BigDecimal(registro[3])));
	        }
	        return listaMeusRendimentos;
	        
		} catch (NoSuchFileException e) {
			List<RendimentosMensais> listaMeusRendimentos = new ArrayList<RendimentosMensais>();
			
			listaMeusRendimentos.add(new RendimentosMensais(1L, "Novo", SimNaoEnum.S, BigDecimal.ZERO));
			
			return salvarMeusRendimentos(listaMeusRendimentos);
		}
	}

}
