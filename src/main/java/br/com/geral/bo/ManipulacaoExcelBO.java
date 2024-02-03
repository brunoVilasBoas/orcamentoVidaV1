package br.com.geral.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import br.com.geral.enums.SimNaoEnum;
import br.com.geral.enums.TipoImportacaoPlanilhasEnum;
import br.com.geral.model.RendimentosMensais;
import br.com.geral.model.GastosMensais;
import br.com.geral.model.MeuDinheiro;

public class ManipulacaoExcelBO {
	
    private static final String NOME_ABA_MEU_DINHEIRO = "ABA MEU DINHEIRO";
    private static final String NOME_ABA_MEUS_GASTOS = "ABA MEUS GASTOS";
    private static final String NOME_ABA_MEUS_GANHOS = "ABA MEUS GANHOS";
    
    private Map<String, String[]> cabecalhos;
    private Map<String, CellStyle> estilos;
	
    
	/**
	 * Método para configuração e geração do planilha
	 * 
	 * @return
	 */
	public Workbook gerarPlanilhaExcel(TipoImportacaoPlanilhasEnum tipoImportacao, List<?> objectList) {
		Workbook wb = new SXSSFWorkbook(100);
		this.cabecalhos = criarCabecalhos(tipoImportacao);
		this.estilos = criarEstilos(wb);
		wb = criarPlanilhas(wb, this.cabecalhos, objectList);
		return wb;
	}
    
    /**
     * Método responsável pelo mapeamento dos cabeçalhos de cada aba da planilha.
     * @return Map<String, String[]> Mapa das colunas de cabeçalho para cada aba da planilha.
     */
	private Map<String, String[]> criarCabecalhos(TipoImportacaoPlanilhasEnum tipoImportacao) {

        Map<String, String[]> cabecalhos = new LinkedHashMap<String, String[]>();
       
		if (tipoImportacao.getCodigo().compareTo(TipoImportacaoPlanilhasEnum.MEU_DINHEIRO.getCodigo()) == 0) {
			String[] empenho = { "ID", "FONTE", "DISPONIVEL", "VALOR" };
			cabecalhos.put(NOME_ABA_MEU_DINHEIRO, empenho);
		}

		if (tipoImportacao.getCodigo().compareTo(TipoImportacaoPlanilhasEnum.MEUS_GANHOS.getCodigo()) == 0) {
			String[] empenho = { "ID", "FONTE", "DISPONIVEL", "VALOR" };
			cabecalhos.put(NOME_ABA_MEUS_GANHOS, empenho);
		}
		
		if (tipoImportacao.getCodigo().compareTo(TipoImportacaoPlanilhasEnum.MEUS_GASTOS.getCodigo()) == 0) {
			String[] empenho = { "ID", "FONTE", "ATIVO", "VALOR" };
			cabecalhos.put(NOME_ABA_MEUS_GASTOS, empenho);
		}

        return cabecalhos;
    }
	
    /**
     * @param wb
     * @param cabecalhos
     * @return Workbook - Planilha
     */
	public Workbook criarPlanilhas(Workbook wb, Map<String, String[]> cabecalhos, List<?> lstObject){
		for (Map.Entry<String, String[]> cabecalho : cabecalhos.entrySet()) {  
			wb = criarAbaWorkbook(wb, cabecalho.getKey(), lstObject);
		}  
		return wb;
	}
	
	/**
	 * Métoto responsável pela criação, padronização e preenchimento da aba da planilha
	 * @param wb
	 * @param nomeAba
	 * @param lstObject 
	 * @return Workbook
	 */
	@SuppressWarnings("unchecked")
	private Workbook criarAbaWorkbook(Workbook wb, String nomeAba, List<?> lstObject) {
    	int indexRow = 0;
    			
    	Sheet sheet = wb.createSheet(nomeAba);
        sheet.setDisplayZeros(false);
        Row rowCabecalho = sheet.createRow(indexRow);
        String[] titulosCabecalho = cabecalhos.get(nomeAba);
        preencherCabecalho(titulosCabecalho, titulosCabecalho.length, rowCabecalho);
        
        formatarColunas(sheet, 0, titulosCabecalho.length, false);
        
        if(nomeAba.equals(NOME_ABA_MEU_DINHEIRO)){
        	// Create the drawing patriarch. This is the top level container for all shapes including cell comments.
            Drawing<?> patr = sheet.createDrawingPatriarch();
        	sheet = preencherAbaMeuDinheiro(patr, sheet, indexRow, (List<MeuDinheiro>) lstObject);
        }
        
        if(nomeAba.equals(NOME_ABA_MEUS_GANHOS)){
        	// Create the drawing patriarch. This is the top level container for all shapes including cell comments.
            Drawing<?> patr = sheet.createDrawingPatriarch();
        	sheet = preencherAbaMeusGanhos(patr, sheet, indexRow, (List<RendimentosMensais>) lstObject);
        }
        
        if(nomeAba.equals(NOME_ABA_MEUS_GASTOS)){
        	// Create the drawing patriarch. This is the top level container for all shapes including cell comments.
            Drawing<?> patr = sheet.createDrawingPatriarch();
        	sheet = preencherAbaMeusGastos(patr, sheet, indexRow, (List<GastosMensais>) lstObject);
        }
        
        return wb;
	  }
	
	/**
     * Método padrão de criação dos Estilos dos campos do relatório.
     * @param wb
     * @return  Map<String, CellStyle> - Mapa com o título e seus referidos estilos.
     */
    private Map<String, CellStyle> criarEstilos(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        Font cabecalhoFont = wb.createFont();
        
        CellStyle styleCabecalho = wb.createCellStyle();
        styles.put("cabecalho", criarEstiloCabecalho(styleCabecalho, cabecalhoFont, HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex()));
        
        CellStyle stylePadrao = wb.createCellStyle();
        styles.put("formatoPadrao", criarEstiloPadrao(wb, stylePadrao, cabecalhoFont, HSSFColor.HSSFColorPredefined.WHITE.getIndex()));
        
        return styles;
    }
    
    /**
     * Método padrão aplicado ao estilo do Cabeçalho.
     * @param styleCabecalho
     * @param cabecalhoFont
     * @param backgroundColor
     * @return CellStyle - Estilo formato/aplicado
     */
    private CellStyle criarEstiloCabecalho(CellStyle styleCabecalho, Font cabecalhoFont, short backgroundColor) {
        cabecalhoFont.setBold(true);
        styleCabecalho.setFont(cabecalhoFont);
        styleCabecalho.setAlignment(HorizontalAlignment.CENTER);
        styleCabecalho.setWrapText(true);
        
        //Cor de Fundo da Celula
        styleCabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleCabecalho.setFillForegroundColor(backgroundColor);
       
        aplicarBorda(styleCabecalho, true, true, true, true);
        return styleCabecalho;
    }
    
    /**
     * Método padrão aplicado ao estilo do campos padrões do relatório
     * @param wb
     * @param styleFormatoMoeda
     * @param cabecalhoFont
     * @param backgroundColor
     * @return CellStyle - Estilo formato/aplicado
     */
    private CellStyle criarEstiloPadrao(Workbook wb, CellStyle stylePadrao, Font cabecalhoFont, short backgroundColor){
    	stylePadrao.setAlignment(HorizontalAlignment.CENTER);
        stylePadrao.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        stylePadrao.setFillForegroundColor(backgroundColor);
        aplicarBorda(stylePadrao, true, true, true, true);
    	return stylePadrao;
    }
    
    /**
     * Método para aplicar borda padrão na célula
     * @param styleCabecalho
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    private void aplicarBorda(CellStyle styleCabecalho, Boolean top, Boolean bottom, Boolean left, Boolean right) {
        // TOP
        if (top != null) {
            styleCabecalho.setBorderTop(top ? BorderStyle.THIN  : BorderStyle.NONE);
            styleCabecalho.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        }
        // BOTTOM
        if (bottom != null) {
            styleCabecalho.setBorderBottom(bottom ? BorderStyle.THIN  : BorderStyle.NONE);
            styleCabecalho.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        }
        // LEFT
        if (left != null) {
            styleCabecalho.setBorderLeft(left ? BorderStyle.THIN  : BorderStyle.NONE);
            styleCabecalho.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        }
        // RIGHT
        if (right != null) {
            styleCabecalho.setBorderRight(right ? BorderStyle.THIN  : BorderStyle.NONE);
            styleCabecalho.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        }
    }
    
	/**
	 * Método para preencher os valores das colunas do cabeçalho e aplicar o estilo padrão de cabeçalho.
	 * @param titulos
	 * @param quantidade
	 * @param row
	 */
	private void preencherCabecalho(String[] titulos, int quantidade, Row row) {
		for (int i = 0; i < quantidade; i++) {
			row.createCell(i).setCellValue(titulos[i]);
			row.getCell(i).setCellStyle(this.estilos.get("cabecalho"));
		}
	}
	
	/**
	 * Método para formatar as colunas (largura)
	 * @param sheet
	 * @param colunaInicio
	 * @param colunaFim
	 * @param autoSize
	 */
	private void formatarColunas(Sheet sheet, int colunaInicio, int colunaFim, boolean autoSize) {
	
		for (int col = colunaInicio; col <= colunaFim; col++) {
	        if (autoSize) {
	            sheet.autoSizeColumn(col);
	        } else {
	            sheet.setColumnWidth(col, 20 * 256);
	        }
	    }
	}
	
	
	private Sheet preencherAbaMeuDinheiro(Drawing<?> patr, Sheet sheet, int indexRow, List<MeuDinheiro> objectList) {
		int indexCampo;
		
		for (MeuDinheiro registro : objectList) {
			indexRow++;
			indexCampo = 0;

			Row linha = sheet.createRow(indexRow);

			indexCampo = criarCampoPadrao(indexCampo, registro.getId(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getFonte(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getDisponivel(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getValor(), linha);

		}

		return sheet;
	}
	
	private Sheet preencherAbaMeusGastos(Drawing<?> patr, Sheet sheet, int indexRow, List<GastosMensais> objectList) {
		int indexCampo;
		
		for (GastosMensais registro : objectList) {
			indexRow++;
			indexCampo = 0;

			Row linha = sheet.createRow(indexRow);

			indexCampo = criarCampoPadrao(indexCampo, registro.getId(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getFonte(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getAtivo(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getValor(), linha);

		}

		return sheet;
	}
	
	private Sheet preencherAbaMeusGanhos(Drawing<?> patr, Sheet sheet, int indexRow, List<RendimentosMensais> objectList) {
		int indexCampo;
		
		for (RendimentosMensais registro : objectList) {
			indexRow++;
			indexCampo = 0;

			Row linha = sheet.createRow(indexRow);

			indexCampo = criarCampoPadrao(indexCampo, registro.getId(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getFonte(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getAtivo(), linha);
			indexCampo = criarCampoPadrao(indexCampo, registro.getValor(), linha);

		}

		return sheet;
	}
	
	/**
	 * Método para padronizar a criação do Campo conforme conteúdo do Objeto
	 * @param indexCampo
	 * @param obj
	 * @param linha
	 * @return int - Número do Index do Próximo Campo
	 */
	private int criarCampoPadrao(int indexCampo, Object obj, Row linha){
		
		if(obj != null && !(obj instanceof MeuDinheiro)){
			if(obj instanceof BigDecimal){
				linha.createCell(indexCampo).setCellValue(((BigDecimal) obj).doubleValue());
				linha.getCell(indexCampo);
			}else if(obj instanceof Long){
				linha.createCell(indexCampo).setCellValue((Long) obj);
				linha.getCell(indexCampo);
			}else{
				linha.createCell(indexCampo).setCellValue((String) obj);
				linha.getCell(indexCampo);
			}
		}
		
		if(obj != null && !(obj instanceof GastosMensais)){
			if(obj instanceof BigDecimal){
				linha.createCell(indexCampo).setCellValue(((BigDecimal) obj).doubleValue());
				linha.getCell(indexCampo);
			}else if(obj instanceof Long){
				linha.createCell(indexCampo).setCellValue((Long) obj);
				linha.getCell(indexCampo);
			}else{
				linha.createCell(indexCampo).setCellValue((String) obj);
				linha.getCell(indexCampo);
			}
		}
		
		if(obj != null && !(obj instanceof RendimentosMensais)){
			if(obj instanceof BigDecimal){
				linha.createCell(indexCampo).setCellValue(((BigDecimal) obj).doubleValue());
				linha.getCell(indexCampo);
			}else if(obj instanceof Long){
				linha.createCell(indexCampo).setCellValue((Long) obj);
				linha.getCell(indexCampo);
			}else{
				linha.createCell(indexCampo).setCellValue((String) obj);
				linha.getCell(indexCampo);
			}
		}
		
		indexCampo++;
		return indexCampo;
	}

	public void criarArquivoExcel(List<?> objectList) {
	
		// Criando o arquivo e uma planilha chamada "Product"
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Product");
	
		try {
		
			//Escrevendo o arquivo em disco
			FileOutputStream out = new FileOutputStream(new File("/tmp/products.xls"));
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("Success!!");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}	
	
	
	private static List<GastosMensais> getlistGastosMensais() {
		
		List<GastosMensais> listaGastosMensais = new ArrayList<GastosMensais>();
		
		listaGastosMensais.add(new GastosMensais(1L, "Luz", SimNaoEnum.S, SimNaoEnum.S, new BigDecimal(360)));
		listaGastosMensais.add(new GastosMensais(2L, "Vivo", SimNaoEnum.S, SimNaoEnum.S, new BigDecimal(60)));
		listaGastosMensais.add(new GastosMensais(3L, "Psicologo", SimNaoEnum.N, SimNaoEnum.S, new BigDecimal(400)));
		
		return listaGastosMensais;
	}
	
	private static List<MeuDinheiro> getlistMeuDinheiro() {
		
		List<MeuDinheiro> listaMeuDinheiro = new ArrayList<MeuDinheiro>();
		
		listaMeuDinheiro.add(new MeuDinheiro(1L, "Nubank", SimNaoEnum.S, new BigDecimal(40162.04)));
		listaMeuDinheiro.add(new MeuDinheiro(2L, "Itáu", SimNaoEnum.S, new BigDecimal(25131.47)));
		listaMeuDinheiro.add(new MeuDinheiro(3L, "FGTS", SimNaoEnum.N, new BigDecimal(41875.13)));

		return listaMeuDinheiro;
	}
	
	private static List<RendimentosMensais> getlistGanhosMensais() {
		
		List<RendimentosMensais> listaGanhosMensais = new ArrayList<RendimentosMensais>();

		listaGanhosMensais.add(new RendimentosMensais(1L, "Nubank", SimNaoEnum.S, new BigDecimal(40162.04)));
		listaGanhosMensais.add(new RendimentosMensais(2L, "Vale Alimentação", SimNaoEnum.S, new BigDecimal(951.72)));
		listaGanhosMensais.add(new RendimentosMensais(3L, "Rendimento Nu", SimNaoEnum.S, new BigDecimal(338.45)));
	 
	
		return listaGanhosMensais;
	}
}	