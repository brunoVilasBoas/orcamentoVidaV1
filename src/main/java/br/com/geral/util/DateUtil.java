package br.com.geral.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public static String dataFormatadaBr(Date data){
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return simpleDateFormat.format(data);
	}
	
	public static Date stringParaDate(String data) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

		return formatter.parse(data);
	}
	

    public static boolean validarDataBrasileira(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false); // Impede datas inv�lidas, como 31 de fevereiro

        try {
            @SuppressWarnings("unused")
			Date dataValidada = formato.parse(data);
            // Se a data foi parseada com sucesso, ent�o � uma data v�lida
            return true;
        } catch (ParseException e) {
            // Se ocorrer uma exce��o, a data � inv�lida
            return false;
        }
    }
}
